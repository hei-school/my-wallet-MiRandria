<?php

class Wallet {
    private $solde;
    private $documents;

    public function __construct($solde_initial = 0) {
        $this->solde = $solde_initial;
        $this->documents = [
            'cin' => null,
            'carteBancaire' => null,
            'permis' => null
        ];
    }

    public function afficherSolde() {
        echo "Solde actuel: {$this->solde} euros\n";
    }

    public function deposer($montant) {
        $this->solde += $montant;
        echo "Dépôt de {$montant} euros effectué avec succès.\n";
        $this->afficherSolde();
    }

    public function retirer($montant) {
        if ($montant <= $this->solde) {
            $this->solde -= $montant;
            echo "Retrait de {$montant} euros effectué avec succès.\n";
            $this->afficherSolde();
        } else {
            echo "Solde insuffisant pour effectuer le retrait.\n";
        }
    }

    public function afficherDocuments() {
        echo "Documents actuels:\n";
        foreach ($this->documents as $document => $valeur) {
            echo "{$document}: " . ($valeur ? "Présent" : "Absent") . "\n";
        }
    }

    public function ajouterDocument($type, $document) {
        if (array_key_exists($type, $this->documents)) {
            $this->documents[$type] = $document;
            echo "{$type} ajouté avec succès.\n";
            $this->afficherDocuments();
        } else {
            echo "Type de document invalide.\n";
        }
    }

    public function retirerDocument($type) {
        if (array_key_exists($type, $this->documents)) {
            $this->documents[$type] = null;
            echo "{$type} retiré avec succès.\n";
            $this->afficherDocuments();
        } else {
            echo "Type de document invalide.\n";
        }
    }
}

function executerCommandes() {
    $wallet = new Wallet();

    while (true) {
        echo "Entrez une commande (solde, depot, retrait, documents, quitter): ";
        $commande = strtolower(trim(fgets(STDIN)));

        switch ($commande) {
            case "solde":
                $wallet->afficherSolde();
                break;
            case "depot":
                echo "Entrez le montant du dépôt : ";
                $montantDepot = floatval(trim(fgets(STDIN)));
                $wallet->deposer($montantDepot);
                break;
            case "retrait":
                echo "Entrez le montant du retrait : ";
                $montantRetrait = floatval(trim(fgets(STDIN)));
                $wallet->retirer($montantRetrait);
                break;
            case "documents":
                echo "Voulez-vous ajouter ou retirer un document ? ";
                $action = strtolower(trim(fgets(STDIN)));
                if ($action == "ajouter") {
                    echo "Entrez le type de document (CIN, carteBancaire, permis) : ";
                    $typeDocument = strtolower(trim(fgets(STDIN)));
                    echo "Entrez le numéro de {$typeDocument} : ";
                    $numeroDocument = trim(fgets(STDIN));
                    $wallet->ajouterDocument($typeDocument, $numeroDocument);
                } elseif ($action == "retirer") {
                    echo "Entrez le type de document à retirer (CIN, carteBancaire, permis) : ";
                    $typeDocument = strtolower(trim(fgets(STDIN)));
                    $wallet->retirerDocument($typeDocument);
                } else {
                    echo "Action invalide.\n";
                }
                break;
            case "quitter":
                echo "Fermeture du programme.\n";
                return;
            default:
                echo "Commande invalide. Veuillez réessayer.\n";
        }
    }
}

executerCommandes();
