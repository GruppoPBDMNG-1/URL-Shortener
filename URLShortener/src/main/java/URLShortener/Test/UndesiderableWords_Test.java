package URLShortener.Test;


import static org.junit.Assert.*;

import URLShortener.Utility.UndesiderableWords;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UndesiderableWords_Test extends TestCase {

	public String[] cases = { "topa", "ciao", "gay", "casa", "mare", "cacca",
			"pirla", "vaffanculo", "cazzo", "culo", "bastardo", "coglione",
			"Cacchio", "Vaiacagare", "figa", "troia", "minchia", "merda",
			"pirla", "stronzo", "fica", "fregna", "mignotta", "puttana",
			"zoccola", "gay", "sborra", "cornuto", "frocio", "anale", "Idiot",
			"Maudit", "Niquetamere", "Putain", "Pute", "Sot", "VaauDiable",
			"Baiser", "Bite", "Chatte", "Couillons", "Cul", "TrouduCul",
			"Enculer", "Fesse", "Fouttre", "Merde", "Niquer", "Pet", "Peter",
			"Pipe", "Pisse", "Pisser", "Putain", "Salope", "Motherfucker",
			"Zizigot", "JackAss", "MindyourownFuckingbusinnes", "fuck",
			"SinistreAbruti", "CassePieds", "CasseCoullies", "Connard",
			"ciaomamma", "tivogliobene", "ipocrisia", "slealta", "odio",
			"basididati", "vincenzo", "molfetta", "fabiola", "bubici",
			"sistudia", "diestate", "facaldo"

	};

	public boolean[] result = { false, true, false, true, true, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, true, true, true, true, true, true, true,
			true, true, true, true, true, true

	};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckUndesiderableWords() {
		for (int i = 0; i < cases.length; i++) {
			boolean c = UndesiderableWords.checkUndesiderableWords(cases[i]);

			assertTrue(c == result[i]);
		}

	}

}
