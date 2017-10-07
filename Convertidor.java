package examen;

import java.util.Scanner;

public class Convertidor {
	private static final String[] UNITS = { "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho",
			"nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho",
			"diecinueve", "veinte" };

	private static final String[] TENS = { "cien", "veinti", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta",
			"ochenta", "noventa" };

	private static final String[] HUNDREDS = { "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
			"seiscientos", "setecientos", "ochocientos", "novecientos" };

	private static String convertNumberToLetter(float n) {
		String converted = "", sign = "";
		float positive = Math.abs(n);

		if (positive == 0) {
			return "cero";
		}

		if (n < 0) {
			sign = "menos ";
		}

		if (positive == 1000) {
			return sign + " mil";
		}

		String[] array = Float.toString(positive).split("\\.");

		if (!array[1].equals("0")) {
			if (array[1].startsWith("0")) {
				converted = sign + convertNumber(array[0]) + " punto cero " + convertNumber(array[1]);
			} else {
				converted = sign + convertNumber(array[0]) + " punto " + convertNumber(array[1]);
			}
		} else {
			converted = sign + convertNumber(Integer.toString((int) positive));
		}

		return converted;
	}

	private static String convertNumber(String input) {
		int i = 0, j = 0;
		StringBuilder aux = new StringBuilder(input);
		StringBuilder converted = new StringBuilder();

		while (aux.length() > 0) {
			i = Integer.parseInt(aux.toString());
			j = Integer.parseInt(aux.substring(0, 1));

			if (aux.length() > 2 && i > 100) {
				converted.append(HUNDREDS[j - 1]);
				converted.append(" ");
			} else {
				if (i > 20) {
					converted.append(TENS[j - 1]);
					if (i >= 30) {
						converted.append(" y ");
					}
				} else {
					if (i == 0) {
						if (converted.indexOf("y") > 0) {
							converted.deleteCharAt(converted.indexOf("y"));
						}
						break;
					}
					converted.append(UNITS[i]);
					break;
				}
			}
			aux.delete(0, 1);
		}
		return converted.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		float number;

		try {
			while (true) {
				System.out.println("Ingrese un número de -1000 a 1000");
				input = sc.nextLine();

				number = Float.parseFloat(input);
				if (number <= 1000 && number >= -1000) {
					String letters = convertNumberToLetter(number);
					System.out.println(letters);
				} else {
					System.out.println("El número no se encuentra en el rango válido, no es posible convertirlo");
				}
			}

		} catch (NumberFormatException nfe) {
			System.out.println("número inválido");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sc.close();
		}
	}
}
