class Portefeuille:
    def __init__(self, solde_initial=0):
        self.solde = solde_initial
        self.documents = {
            'cin': None,
            'carteBancaire': None,
            'permis': None
        }

    def afficher_solde(self):
        print(f"Solde actuel: {self.solde} euros")

    def deposer(self, montant):
        self.solde += montant
        print(f"Dépôt de {montant} euros effectué avec succès.")
        self.afficher_solde()

    def retirer(self, montant):
        if montant <= self.solde:
            self.solde -= montant
            print(f"Retrait de {montant} euros effectué avec succès.")
            self.afficher_solde()
        else:
            print("Solde insuffisant pour effectuer le retrait.")

    def afficher_documents(self):
        print("Documents actuels:")
        for document, valeur in self.documents.items():
            print(f"{document}: {'Présent' if valeur else 'Absent'}")

    def ajouter_document(self, type_document, document):
        if type_document in self.documents:
            self.documents[type_document] = document
            print(f"{type_document} ajouté avec succès.")
            self.afficher_documents()
        else:
            print("Type de document invalide.")

    def retirer_document(self, type_document):
        if type_document in self.documents:
            self.documents[type_document] = None
            print(f"{type_document} retiré avec succès.")
            self.afficher_documents()
        else:
            print("Type de document invalide.")

def executer_commandes():
    portefeuille = Portefeuille()

    while True:
        commande = input("Entrez une commande (solde, depot, retrait, documents, quitter): ").lower()

        if commande == "solde":
            portefeuille.afficher_solde()
        elif commande == "depot":
            montant_depot = float(input("Entrez le montant du dépôt : "))
            portefeuille.deposer(montant_depot)
        elif commande == "retrait":
            montant_retrait = float(input("Entrez le montant du retrait : "))
            portefeuille.retirer(montant_retrait)
        elif commande == "documents":
            action = input("Voulez-vous ajouter ou retirer un document ? ").lower()
            if action == "ajouter":
                type_document = input("Entrez le type de document (CIN, carteBancaire, permis) : ")
                numero_document = input(f"Entrez le numéro de {type_document} : ")
                portefeuille.ajouter_document(type_document.lower(), numero_document)
            elif action == "retirer":
                type_document = input("Entrez le type de document à retirer (CIN, carteBancaire, permis) : ")
                portefeuille.retirer_document(type_document.lower())
            else:
                print("Action invalide.")
        elif commande == "quitter":
            print("Fermeture du programme.")
            break
        else:
            print("Commande invalide. Veuillez réessayer.")

if __name__ == "__main__":
    executer_commandes()
