import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.fest.assertions.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.Test;


public class GildedRoseTest {

	@Test
	public void test1() {
        GildedRose.items = Lists.newArrayList();

		GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).isEmpty();
    }

    @Test
	public void anyItemShouldDecreaseQuality() {
        GildedRose.items = Lists.newArrayList(anItem("any",5,10));

		GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(anItem("any",4,9));
    }

    @Test
	public void agedBrie() {
        GildedRose.items = Lists.newArrayList(anItem("Aged Brie",5,10));

		GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(anItem("Aged Brie",4,11));
    }

    @Test
	public void backstagePasses() {
        GildedRose.items = Lists.newArrayList(anItem("Backstage passes to a TAFKAL80ETC concert",5,10));

		GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(
                anItem("Backstage passes to a TAFKAL80ETC concert",4,13));
    }

    @Test
    public void sellIn0() {
        GildedRose.items = Lists.newArrayList(anItem("Backstage passes to a TAFKAL80ETC concert",0,10));

        GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(
                anItem("Backstage passes to a TAFKAL80ETC concert",-1,0));
    }

    @Test
    public void sellIn0Any() {
        GildedRose.items = Lists.newArrayList(anItem("Any",0,10));

        GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(
                anItem("Any",-1,8));
    }

    @Test
    public void sellIn0AgedBrie() {
        GildedRose.items = Lists.newArrayList(anItem("Aged Brie",0,10));

        GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(
                anItem("Aged Brie",-1,12));
    }

    @Test
    public void sulfuras() {
        GildedRose.items = Lists.newArrayList(anItem("Sulfuras, Hand of Ragnaros",0,10));

        GildedRose.updateQuality();

        Assertions.assertThat(GildedRose.items).containsExactly(
                anItem("Sulfuras, Hand of Ragnaros",0,10));
    }

    private Item anItem(String itemName, int sellIn, int quality) {
        return new MyItem(itemName,sellIn,quality);
    }

    private class MyItem extends Item {
        public MyItem(String itemName, int sellIn, int quality) {
            super(itemName,sellIn,quality);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
        }

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this,obj);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }
}
