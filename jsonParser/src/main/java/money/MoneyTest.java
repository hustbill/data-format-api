package money;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Locale;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryRounding;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

/*
 * What is the best Java data type to use for currency?
 * 
 * http://stackoverflow.com/questions/8148684/what-is-the-best-data-type-to-use-for-money-in-java-app
 *  use Money and Currency API (JSR 354). This API is expected to be part of Java 9.
 *   You can use this API in Java 7 and Java 8, provided you add appropriate dependencies to your project.
 *   For Java 8, add the following reference implementation as a dependency to your pom.xml:

<dependency>
    <groupId>org.javamoney</groupId>
    <artifactId>moneta</artifactId>
    <version>1.0</version>
</dependency>

This dependency will transitively add javax.money:money-api as a dependency.
 */

public class MoneyTest {

	@Test
	 public void testMoneyApi() {
        MonetaryAmount eurAmount1 = Monetary.getDefaultAmountFactory().setNumber(1.1111).setCurrency("EUR").create();
        MonetaryAmount eurAmount2 = Monetary.getDefaultAmountFactory().setNumber(1.1141).setCurrency("EUR").create();

        MonetaryAmount eurAmount3 = eurAmount1.add(eurAmount2);
        assertThat(eurAmount3.toString(), is("EUR 2.2252"));

        MonetaryRounding defaultRounding = Monetary.getDefaultRounding();
        MonetaryAmount eurAmount4 = eurAmount3.with(defaultRounding);
        assertThat(eurAmount4.toString(), is("EUR 2.23"));

        MonetaryAmountFormat germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMAN);
        assertThat(germanFormat.format(eurAmount4), is("EUR 2,23") );


    }

}

