package academy.prog;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// DB -> E(20) -> R -> S -> DTO <- C -> View / JSON (5)

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @Scheduled(fixedRate = 60000)
    public void deleteExpiredUrls() {
        long inactiveTimeLimit = 60000;
        Date currentTime = new Date();

        List<UrlRecord> expiredRecords = urlRepository.findAll().stream()
                .filter(record -> record.getLastAccess() == null ||
                        (currentTime.getTime() - record.getLastAccess().getTime()) > inactiveTimeLimit)
                .collect(Collectors.toList());

        urlRepository.deleteAll(expiredRecords);
    }

    @Transactional
    public boolean deleteShortUrl(UrlResultDTO urlResultDTO) {
        var urlRecord = urlRepository.findByUrl(urlResultDTO.getUrl());
        if (urlRecord != null) {
            urlRepository.delete(urlRecord);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteShortUrl(Long id) {
        var urlRecord = urlRepository.findById(id);
        if (urlRecord.isEmpty())
            return false;
        var record = urlRecord.get();
        urlRepository.delete(record);
        return true;
    }

    @Transactional
    public long saveUrl(UrlDTO urlDTO) {
        var urlRecord = urlRepository.findByUrl(urlDTO.getUrl());
        if (urlRecord == null) {
            urlRecord = UrlRecord.of(urlDTO);
            urlRepository.save(urlRecord);
        }

        return urlRecord.getId();
    }

    @Transactional
    public String getUrl(long id) {
        var urlOpt = urlRepository.findById(id);
        if (urlOpt.isEmpty())
            return null;

        var urlRecord = urlOpt.get();
        urlRecord.setCount(urlRecord.getCount() + 1);
        urlRecord.setLastAccess(new Date());

        return urlRecord.getUrl();
    }

    @Transactional(readOnly = true)
    public List<UrlStatDTO> getStatistics() {
        var records = urlRepository.findAll();
        var result = new ArrayList<UrlStatDTO>();

        records.forEach(x -> result.add(x.toStatDTO()));

        return result;
    }
}
