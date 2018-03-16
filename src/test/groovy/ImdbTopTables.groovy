import org.junit.Test
import geb.Browser

class ImdbTopTables {

    @Test
    void GetTopList(){
        Browser.drive {
            go ("http://www.imdb.com/chart/top/?sort=ir,desc&mode=simple&page=1")
            def box_list = $(".lister-list")

            def sells = box_list.$("tr")

            File file = new File("top250imdb.txt")

            def titleColumn
            def title_href
            def title_href_title
            def rateColumn
            def rateTitle

            int idx = 0

            while (idx <= sells.size() - 1){
                titleColumn = sells[idx].$(".titleColumn")
                title_href = titleColumn.$("a")
                title_href_title = title_href.getAttribute("title")
                rateColumn = sells[0].$(".ratingColumn.imdbRating")
                rateTitle = rateColumn.$("strong").getAttribute("title")

                println(title_href.text())

                file << titleColumn.text() + "\n"
                file << rateTitle + "\n"
                file << title_href_title + "\n\n"
                idx++
            }
        }
    }
}
