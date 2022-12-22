public class UpDownTransformer extends TextTransformer {

    @Override
    public String transform(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }
        }
        return sb.toString();
    }
}