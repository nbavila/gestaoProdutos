package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) {
		// Configurar a localização padrão para os números e datas (no formato dos EUA).
		Locale.setDefault(Locale.US);

		// Criar um objeto Scanner para a entrada do usuário.
		Scanner sc = new Scanner(System.in);

		// Criar uma lista para armazenar os produtos.
		List<Product> list = new ArrayList<Product>();

		// Solicitar ao usuário o número de produtos que deseja adicionar.
		System.out.print("Entre com o numero de produtos: ");
		int n = sc.nextInt();

		// Loop para receber informações sobre cada produto.
		for (int i = 1; i <= n; i++) {
			System.out.println("Produto #" + i + " dados: ");
			System.out.print("Comum, usado, importado (c/u/i): ");
			char option = sc.next().charAt(0);
			System.out.print("Nome: ");
			String name = sc.next();
			System.out.print("Preco: ");
			double price = sc.nextDouble();

			// Verificar a opção do usuário e criar o tipo de produto apropriado.
			if (option == 'c' || option == 'C') {
				list.add(new Product(name, price));
			} else if (option == 'u' || option == 'U') {
				System.out.print("Data de fabricacao (DD/MM/YYYY): ");
				// Ler e analisar a data no formato correto.
				LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				list.add(new UsedProduct(name, price, date));
			} else if (option == 'i' || option == 'I') {
				System.out.print("Taxa de importacao: ");
				double customFee = sc.nextDouble();
				list.add(new ImportedProduct(name, price, customFee));
			}
		}

		// Imprimir uma linha em branco para separar a entrada dos produtos da saída.
		System.out.println();
		System.out.println("Etiquetas de preco: ");

		// Loop para exibir as etiquetas de preço de cada produto.
		for (Product prod : list) {
			System.out.println(prod.priceTag());
		}

		// Fechar o scanner.
		sc.close();
	}

}
