const readline = require('readline');

class Portefeuille {
    constructor(solde_initial = 0) {
        this.solde = solde_initial;
        this.documents = {
            cin: null,
            carteBancaire: null,
            permis: null
        };
    }

    afficherSolde() {
        console.log(`Solde actuel: ${this.solde} euros`);
    }

    deposer(montant) {
        this.solde += montant;
        console.log(`Dépôt de ${montant} euros effectué avec succès.`);
        this.afficherSolde();
    }

    retirer(montant) {
        if (montant <= this.solde) {
            this.solde -= montant;
            console.log(`Retrait de ${montant} euros effectué avec succès.`);
            this.afficherSolde();
        } else {
            console.log("Solde insuffisant pour effectuer le retrait.");
        }
    }

    afficherDocuments() {
        console.log("Documents actuels:");
        for (const [document, valeur] of Object.entries(this.documents)) {
            console.log(`${document}: ${valeur ? "Présent" : "Absent"}`);
        }
    }

    ajouterDocument(type, document) {
        if (type in this.documents) {
            this.documents[type] = document;
            console.log(`${type} ajouté avec succès.`);
            this.afficherDocuments();
        } else {
            console.log("Type de document invalide.");
        }
    }

    retirerDocument(type) {
        if (type in this.documents) {
            this.documents[type] = null;
            console.log(`${type} retiré avec succès.`);
            this.afficherDocuments();
        } else {
            console.log("Type de document invalide.");
        }
    }
}

function executerCommandes() {
    const portefeuille = new Portefeuille();

    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    function questionUtilisateur(question) {
        return new Promise(resolve => rl.question(question, resolve));
    }

    async function gestionCommandes() {
        while (true) {
            const commande = (await questionUtilisateur("Entrez une commande (solde, depot, retrait, documents, quitter): ")).toLowerCase();

            if (commande === "solde") {
                portefeuille.afficherSolde();
            } else if (commande === "depot") {
                const montantDepot = parseFloat(await questionUtilisateur("Entrez le montant du dépôt : "));
                portefeuille.deposer(montantDepot);
            } else if (commande === "retrait") {
                const montantRetrait = parseFloat(await questionUtilisateur("Entrez le montant du retrait : "));
                portefeuille.retirer(montantRetrait);
            } else if (commande === "documents") {
                const action = (await questionUtilisateur("Voulez-vous ajouter ou retirer un document ? ")).toLowerCase();
                if (action === "ajouter") {
                    const typeDocument = await questionUtilisateur("Entrez le type de document (CIN, carteBancaire, permis) : ");
                    const document = await questionUtilisateur(`Entrez le numéro de ${typeDocument} : `);
                    portefeuille.ajouterDocument(typeDocument.toLowerCase(), document);
                } else if (action === "retirer") {
                    const typeDocument = await questionUtilisateur("Entrez le type de document à retirer (CIN, carteBancaire, permis) : ");
                    portefeuille.retirerDocument(typeDocument.toLowerCase());
                } else {
                    console.log("Action invalide.");
                }
            } else if (commande === "quitter") {
                console.log("Fermeture du programme.");
                rl.close();
                return;
            } else {
                console.log("Commande invalide. Veuillez réessayer.");
            }
        }
    }

    gestionCommandes().then(() => rl.close());
}

executerCommandes();
