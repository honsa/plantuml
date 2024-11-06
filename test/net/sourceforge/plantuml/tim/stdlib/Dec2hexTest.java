package net.sourceforge.plantuml.tim.stdlib;

import static net.sourceforge.plantuml.tim.TimTestUtils.assertTimExpectedOutput;
import static net.sourceforge.plantuml.tim.TimTestUtils.assertTimExpectedOutputFromInput;

import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.TFunction;

/**
 * Tests the builtin function.
 */
@IndicativeSentencesGeneration(separator = ": ", generator = ReplaceUnderscores.class)

class Dec2hexTest {
	TFunction cut = new Dec2hex();
	final String cutName = "Dec2hex";

	@Test
	void Test_without_Param() throws EaterException {
		assertTimExpectedOutput(cut, "");
	}

	@ParameterizedTest(name = "[{index}] " + cutName + "(''{0}'') = {1}")
	@CsvSource(nullValues = "null", value = {
			" 0     , 0 ",
			" 1     , 0 ",
			" 10    , 0 ",
			" 15    , 0 ",
			" 16    , 0 ",
			" 255   , 0 ",
			" 65535 , 0 ",
	})
	void Test_with_String(String input, String expected) throws EaterException {
		assertTimExpectedOutputFromInput(cut, input, expected);
	}

	@ParameterizedTest(name = "[{index}] " + cutName + "({0}) = {1}")
	@CsvSource(nullValues = "null", value = {
			" 0     , 0 ",
			" 1     , 1 ",
			" 10    , a ",
			" 15    , f ",
			" 16    , 10 ",
			" 255   , ff ",
			" 65535 , ffff ",
	})
	void Test_with_Integer(Integer input, String expected) throws EaterException {
		assertTimExpectedOutputFromInput(cut, input, expected);
	}
}
