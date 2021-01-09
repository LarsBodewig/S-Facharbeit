public class Alk {
	private Fach[] faecher;
	private Schueler[] stufe;
	private double larechsu;

	private class Fach {
		private String name;
		private String lehrer;
		private int platz;

		public Fach(String n, String lehr, int platz) {
			name = n;
			this.platz = platz;
			lehrer = lehr;
		}

		public String getName() {
			return name;
		}

		public String getLehr() {
			return lehrer;
		}

		public int platz() {
			return platz;
		}
	}

	private class Schueler {
		private String name;
		private String[] wahl;

		public Schueler(String name, String[] wahl) {
			this.wahl = wahl;
			this.name = name;
		}

		public String get(int u) {
			if (u >= 0 && u < wahl.length) {
				return wahl[u];
			}
			return name;
		}
	}

	public Alk(String[][] f, String[] e, String[][] g) {
		erzeugeStufe(e, g);
		erzeugeF(f);
	}

	public double larechsu() {
		return larechsu;
	}

	private void erzeugeF(String[][] f) {
		faecher = new Fach[f.length];
		for (int i = 0; i < faecher.length; i++) {
			faecher[i] = new Fach(f[i][0], f[i][1], Integer.valueOf(f[i][2]));
		}
	}

	private void erzeugeStufe(String[] f, String[][] g) {
		stufe = new Schueler[f.length];
		for (int i = 0; i < stufe.length; i++) {
			stufe[i] = new Schueler(f[i], g[i]);
		}
	}

	public String[][] resultat(int grPopulation, int evoPro) {
		String[] ersteGen = new String[grPopulation];
		String fittest = randomiser(stufe.length * stufe.length * stufe.length);
		for (int i = 0; i < ersteGen.length; i++) {
			ersteGen[i] = randomiser(stufe.length);
			if (ersteGen[i] == "") {
				ersteGen[i] = fittest;
			} else if (quersumme(fittest) > quersumme(ersteGen[i]) || fittest == "") {
				fittest = ersteGen[i];
			}
		}
		if (fittest == "") {
			return null;
		}
		for (int n = 0; n < evoPro; n++) {
			ersteGen = evoWin(ersteGen);
			for (int r = 0; r < ersteGen.length - 1; r += 2) {
				if ((int) (Math.random() * 3) != 0) {
					int a = (int) (Math.random() * ersteGen[r].length() - 1) + 1;
					String b = ersteGen[r].substring(a);
					ersteGen[r] = ersteGen[r].substring(0, a) + ersteGen[r + 1].substring(a);
					ersteGen[r + 1] = ersteGen[r + 1].substring(0, a) + b;
				}
			}
			sec: for (int l = 0; l < ersteGen.length; l++) {
				int taat = 0;
				char[] c = ersteGen[l].toCharArray();
				do {
					if (taat > stufe.length * 2) {
						ersteGen[l] = fittest;
						continue sec;
					}
					for (int o = 0; o < ersteGen[l].length(); o++) {
						if ((int) (Math.random() * ersteGen[l].length()) == 0) {
							c[o] = Integer.toString((int) (Math.random() * 3)).charAt(0);
							ersteGen[l] = new String(c);
						}
					}
					taat++;
				} while (!full(ersteGen[l]));
				if (quersumme(fittest) > quersumme(ersteGen[l])) {
					fittest = ersteGen[l];
				}
			}
		}
		String[][] in = new String[stufe.length][3];
		for (int t = 0; t < stufe.length; t++) {
			in[t][0] = stufe[t].get(-1);
			for (int j = 0; j < faecher.length; j++) {
				if (stufe[t].get(Character.getNumericValue(fittest.charAt(t))).equalsIgnoreCase(faecher[j].getName())) {
					in[t][1] = faecher[j].getName();
					in[t][2] = faecher[j].getLehr();
				}
			}
		}
		larechsu = (double) quersumme(fittest) / stufe.length;
		return in;
	}

	private int quersumme(String s) {
		int w = 0;
		for (int i = 0; i < s.length(); i++) {
			w += Character.getNumericValue(s.charAt(i));
		}
		return w;
	}

	private boolean full(String s) {
		int[] testFaecher = new int[faecher.length];
		for (int w = 0; w < faecher.length; w++) {
			testFaecher[w] = faecher[w].platz();
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < testFaecher.length; j++) {
				if (stufe[i].get(Character.getNumericValue(s.charAt(i))).equalsIgnoreCase(faecher[j].getName())) {
					if (testFaecher[j] > 0) {
						testFaecher[j]--;
						break;
					}
					return false;
				}
			}
		}
		return true;
	}

	private String[] evoWin(String[] alteGen) {
		String[] neueGen = new String[alteGen.length];
		double d = 0;
		for (int k = 0; k < alteGen.length; k++) {
			d += alteGen[k].length() * 2 - quersumme(alteGen[k]);
		}
		for (int n = 0; n < alteGen.length; n++) {
			double e = Math.random();
			double c = 0;
			for (int l = 0; l < alteGen.length; l++) {
				c += (alteGen[l].length() * 2 - quersumme(alteGen[l])) / d;
				if (e < c && 0 < c) {
					neueGen[n] = alteGen[l];
					break;
				}
			}
		}
		return neueGen;
	}

	private String randomiser(int a) {
		int t = 0;
		String temp;
		do {
			temp = "";
			if (t < a) {
				for (int j = 0; j < stufe.length; j++) {
					temp = new StringBuilder().append(temp).append(Integer.toString((int) (Math.random() * 3)))
							.toString();
				}
			}
			t++;
		} while (!full(temp));
		return temp;
	}
}