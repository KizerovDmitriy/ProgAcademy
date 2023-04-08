package academy.prog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UrlStatDTO extends UrlResultDTO {
    private long redirects;
    private Date lastAccess; // TODO: set normal format

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public long getRedirects() {
        return redirects;
    }

    public void setRedirects(long redirects) {
        this.redirects = redirects;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getLastAccess() {
        if (lastAccess == null) {
            return "";
        }
        return dateFormat.format(lastAccess);
    }
}
