import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wallet {
    private double balance;
    private Map<String, String> documents;

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
        this.documents = new HashMap<>();
        this.documents.put("cin", null);
        this.documents.put("carteBancaire", null);
        this.documents.put("permis", null);
    }

    public void displayBalance() {
        System.out.println("Solde actuel: " + this.balance + " euros");
    }

    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("Dépôt de " + amount + " euros effectué avec succès.");
        displayBalance();
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Retrait de " + amount + " euros effectué avec succès.");
            displayBalance();
        } else {
            System.out.println("Solde insuffisant pour effectuer le retrait.");
        }
    }

    public void displayDocuments() {
        System.out.println("Documents actuels:");
        for (Map.Entry<String, String> entry : documents.entrySet()) {
            String document = entry.getKey();
            String value = entry.getValue() != null ? "Présent" : "Absent";
            System.out.println(document + ": " + value);
        }
    }

    public void addDocument(String type, String document) {
        if (documents.containsKey(type)) {
            documents.put(type, document);
            System.out.println(type + " ajouté avec succès.");
            displayDocuments();
        } else {
            System.out.println("Type de document invalide.");
        }
    }

    public void removeDocument(String type) {
        if (documents.containsKey(type)) {
            documents.put(type, null);
            System.out.println(type + " retiré avec succès.");
            displayDocuments();
        } else {
            System.out.println("Type de document invalide.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet(0);

        while (true) {
            System.out.print("Entrez une commande (solde, depot, retrait, documents, quitter): ");
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "solde":
                    wallet.displayBalance();
                    break;
                case "depot":
                    System.out.print("Entrez le montant du dépôt : ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Pour consommer le retour à la ligne
                    wallet.deposit(depositAmount);
                    break;
                case "retrait":
                    System.out.print("Entrez le montant du retrait : ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Pour consommer le retour à la ligne
                    wallet.withdraw(withdrawAmount);
                    break;
                case "documents":
                    System.out.print("Voulez-vous ajouter ou retirer un document ? ");
                    String action = scanner.nextLine().toLowerCase();
                    if (action.equals("ajouter")) {
                        System.out.print("Entrez le type de document (CIN, carteBancaire, permis) : ");
                        String documentType = scanner.nextLine().toLowerCase();
                        System.out.print("Entrez le numéro de " + documentType + " : ");
                        String documentNumber = scanner.nextLine();
                        wallet.addDocument(documentType, documentNumber);
                    } else if (action.equals("retirer")) {
                        System.out.print("Entrez le type de document à retirer (CIN, carteBancaire, permis) : ");
                        String documentType = scanner.nextLine().toLowerCase();
                        wallet.removeDocument(documentType);
                    } else {
                        System.out.println("Action invalide.");
                    }
                    break;
                case "quitter":
                    System.out.println("Fermeture du programme.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Commande invalide. Veuillez réessayer.");
            }
        }
    }
}
