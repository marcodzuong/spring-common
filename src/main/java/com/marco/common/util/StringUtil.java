package com.marco.common.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author MarcoDuong
 */
public class StringUtil {
    public static final String EMPTY = "";
    private static  final Pattern PATERN_IP = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    public static  boolean isEmpty(Long num){
        return num == null;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        if ("null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }
    static Pattern letterPattern = Pattern.compile("[a-z A-Z]");
    public static  boolean isLetter(char c){
        return letterPattern.matcher(String.valueOf(c)).matches();
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; ++i) {
            if (!(Character.isDigit(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMobile(String phoneNo) {
        if(phoneNo == null){
            return false;
        }
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }

//    public static boolean isEmail(String email) {
//        return EmailValidator.getInstance()
//                .isValid(email);
//    }
//
//    public static boolean hasBlank(String... strs) {
//        if (CollectionKit.isEmpty(strs)) {
//            return true;
//        }
//        for (String str : strs) {
//            if (isEmpty(str)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean isNotEmpty(String str) {
        return (!isEmpty(str));
    }

    public static boolean isNullOrEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (String.valueOf(o).replace((char) 12288, ' ').trim().length() == 0) {
            return true;
        }
        if ("null".equals(o)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNullOrEmpty(Object o) {
        return !isNullOrEmpty(o);
    }


    public static String sNull(Object obj) {
        return obj==null?"":obj.toString();
    }

//    public static String format(String template, Object... values) {
//        if (CollectionKit.isEmpty(values) || isEmpty(template)) {
//            return template;
//        }
//
//        final StringBuilder sb = new StringBuilder();
//        final int length = template.length();
//
//        int valueIndex = 0;
//        char currentChar;
//        for (int i = 0; i < length; i++) {
//            if (valueIndex >= values.length) {
//                sb.append(sub(template, i, length));
//                break;
//            }
//
//            currentChar = template.charAt(i);
//            if (currentChar == '{') {
//                final char nextChar = template.charAt(++i);
//                if (nextChar == '}') {
//                    sb.append(values[valueIndex++]);
//                } else {
//                    sb.append('{').append(nextChar);
//                }
//            } else {
//                sb.append(currentChar);
//            }
//
//        }
//
//        return sb.toString();
//    }

    public static String format(String template, Map<?, ?> map) {
        if (null == map || map.isEmpty()) {
            return template;
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return template;
    }

    public static String sub(String string, int fromIndex, int toIndex) {
        int len = string.length();
        if (fromIndex < 0) {
            fromIndex = len + fromIndex;
            if(fromIndex < 0 ) {
                fromIndex = 0;
            }
        } else if(fromIndex >= len) {
            fromIndex = len -1;
        }
        if (toIndex < 0) {
            toIndex = len + toIndex;
            if(toIndex < 0) {
                toIndex = len;
            }
        } else if(toIndex > len) {
            toIndex = len;
        }
        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }
        if (fromIndex == toIndex) {
            return EMPTY;
        }
        char[] strArray = string.toCharArray();
        char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
        return new String(newStrArray);
    }

    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String removePrefix(String str, String prefix) {
        if(isEmpty(str) || isEmpty(prefix)){
            return str;
        }

        if (str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    public static String removeSuffix(String str, String suffix) {
        if(isEmpty(str) || isEmpty(suffix)){
            return str;
        }

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    public static byte[] getBytes(String str, Charset charset){
        if(null == str){
            return null;
        }
        return null == charset ? str.getBytes() : str.getBytes(charset);
    }
    public static  Long[] splitForLong(String str,String delimiter){
        String[] arr = str.split(delimiter);
        if(arr!=null&&arr.length>0){
            Long[] ret = new Long[arr.length];
            for(int i=0;i<ret.length;i++){
                ret[i] = Long.valueOf(arr[i]);
            }
            return ret;
        }
        return null;
    }

    public static String[] split(String str, String delimiter) {
        if (str == null) {
            return null;
        }
        if (str.trim().length() == 0) {
            return new String[] { str };
        }

        int dellen = delimiter.length(); // del length
        int maxparts = (str.length() / dellen) + 2; // one more for the last
        int[] positions = new int[maxparts];

        int i, j = 0;
        int count = 0;
        positions[0] = -dellen;
        while ((i = str.indexOf(delimiter, j)) != -1) {
            count++;
            positions[count] = i;
            j = i + dellen;
        }
        count++;
        positions[count] = str.length();

        String[] result = new String[count];

        for (i = 0; i < count; i++) {
            result[i] = str.substring(positions[i] + dellen, positions[i + 1]);
        }
        return result;
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    public static byte[] bytes(String str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.getBytes();
        }
        return str.getBytes(charset);
    }

    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    public static String filterSpecialChar(String nickname) {
        if (nickname == null) {
            return null;
        }
        nickname = nickname.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
        return nickname;
    }

}
