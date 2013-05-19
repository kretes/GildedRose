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

    private Item anItem(String itemName, int sellIn, int quality) {
        return new MyItem(itemName,sellIn,quality);
    }

    private class MyItem extends Item {
        public MyItem(String any, int i, int i1) {
            super(any,i,i1);
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
