public class ReverseString {

    public static void main(String[] args) {
        String str = "www.bytedancer.com";

        StringBuilder builder = new StringBuilder();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                builder.append(str.charAt(i));
            } else {
                builder.insert(0, '.');
                result.insert(0, builder.toString());
                builder.delete(0, builder.length());
            }
        }

        result.insert(0, builder.toString());

        System.out.println(result.toString());
    }
}
