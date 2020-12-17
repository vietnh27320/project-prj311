package util;

public class HtmlHelper {

    public static String hyperlink(String label, String href) {
        return "<a class=\"page_hyperlink\" href='" + href + "#test'>" + label + "</a>";
    }

    public static String pager(int pageindex, int pagecount, int gap) {
        String result = "";
        if (pageindex > gap) {
            result += hyperlink(" First ", "get-list-product?page1=" + 1);
        }

        for (int i = gap; i > 0; i--) {
            if (pageindex - i > 0) {
                result += hyperlink(" " + (pageindex - i), "get-list-product?page1=" + (pageindex - i));
            }
        }

        result += "<span class=\"page_pageindex\"> " + pageindex + " </span>";

        for (int i = 1; i <= gap; i++) {
            if (pageindex + i <= pagecount) {
                result += hyperlink("" + (pageindex + i), "get-list-product?page1=" + (pageindex + i));
            }
        }

        if (pageindex + gap <= pagecount) {
            result += hyperlink(" Last ", "get-list-product?page1=" + pagecount);
        }
        return result;
    }
}
