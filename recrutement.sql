-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 26 avr. 2025 à 10:01
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `recrutement`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `id` int NOT NULL,
  `id_utilisateur` int DEFAULT NULL,
  `secteurActivite` varchar(100) DEFAULT NULL,
  `emailContact` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `nomEntreprise` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id`, `id_utilisateur`, `secteurActivite`, `emailContact`, `telephone`, `nomEntreprise`) VALUES
(200, 2, 'Recrutement IT', 'contact@buzzcutagency.com', '0601020304', 'Buzzcut Agency');

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

DROP TABLE IF EXISTS `candidature`;
CREATE TABLE IF NOT EXISTS `candidature` (
  `id` int NOT NULL,
  `id_emploi` int DEFAULT NULL,
  `id_demandeur` int DEFAULT NULL,
  `datePostulation` date DEFAULT NULL,
  `statut` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_emploi` (`id_emploi`),
  KEY `id_demandeur` (`id_demandeur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `candidature`
--

INSERT INTO `candidature` (`id`, `id_emploi`, `id_demandeur`, `datePostulation`, `statut`, `note`) VALUES
(400, 300, 100, '2025-04-25', 'En attente', '4.5'),
(401, 301, 100, '2025-04-25', 'Refusée', '3.8');

-- --------------------------------------------------------

--
-- Structure de la table `demandeur`
--

DROP TABLE IF EXISTS `demandeur`;
CREATE TABLE IF NOT EXISTS `demandeur` (
  `idf` int NOT NULL,
  `id` int DEFAULT NULL,
  `cv` text,
  `profil` text,
  PRIMARY KEY (`idf`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `demandeur`
--

INSERT INTO `demandeur` (`idf`, `id`, `cv`, `profil`) VALUES
(100, 1, 'CV de Loan - Dev Web', 'Développeur web passionné par le java.');

-- --------------------------------------------------------

--
-- Structure de la table `emploi`
--

DROP TABLE IF EXISTS `emploi`;
CREATE TABLE IF NOT EXISTS `emploi` (
  `id` int NOT NULL,
  `id_agence` int DEFAULT NULL,
  `titre` varchar(100) DEFAULT NULL,
  `description` text,
  `typeContrat` varchar(50) DEFAULT NULL,
  `categorie` varchar(100) DEFAULT NULL,
  `remuneration` float DEFAULT NULL,
  `lieu` varchar(100) DEFAULT NULL,
  `datePublication` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_agence` (`id_agence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `emploi`
--

INSERT INTO `emploi` (`id`, `id_agence`, `titre`, `description`, `typeContrat`, `categorie`, `remuneration`, `lieu`, `datePublication`) VALUES
(300, 200, 'Développeur Java', 'Développement d’applications d’entreprise en Java.', 'CDI', 'Informatique', 40000, 'Paris', '2025-04-25'),
(301, 200, 'Data Analyst Junior', 'Analyse de données pour nos clients finance.', 'CDD', 'Data Science', 35000, 'Lyon', '2025-04-24'),
(302, 200, 'Développeur Fullstack', 'Développement front-end et back-end pour applications web modernes.', 'CDI', 'Informatique', 42000, 'Paris', '2025-04-25'),
(303, 200, 'Consultant Cybersécurité', 'Sécurisation des infrastructures informatiques pour nos clients B2B.', 'CDI', 'Sécurité', 45000, 'Rennes', '2025-04-24'),
(304, 200, 'Chef de projet IT', 'Gestion de projets digitaux et pilotage d\'équipes techniques.', 'CDI', 'Gestion de projet', 47000, 'Lyon', '2025-04-23'),
(305, 200, 'Technicien support informatique', 'Support utilisateur et maintenance de parc informatique.', 'CDD', 'Support', 28000, 'Marseille', '2025-04-22'),
(306, 200, 'Ingénieur Cloud', 'Déploiement et gestion d\'infrastructures cloud (AWS, Azure).', 'CDI', 'Cloud Computing', 50000, 'Toulouse', '2025-04-21');

-- --------------------------------------------------------

--
-- Structure de la table `statistique`
--

DROP TABLE IF EXISTS `statistique`;
CREATE TABLE IF NOT EXISTS `statistique` (
  `id` int NOT NULL,
  `id_emploi` int DEFAULT NULL,
  `nombreVue` int DEFAULT NULL,
  `nombrePostulation` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_emploi` (`id_emploi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `statistique`
--

INSERT INTO `statistique` (`id`, `id_emploi`, `nombreVue`, `nombrePostulation`) VALUES
(500, 300, 120, 15),
(501, 301, 80, 8);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `dateCreation` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `mail`, `motDePasse`, `type`, `dateCreation`) VALUES
(1, 'Loan Sournois', 'loan.sournois@mail.com', '123', 'demandeur', '12/07/2024'),
(2, 'Mathias Buzzcut', 'mathias.buzzcut@mail.com', '123', 'agence', '24/12/2022');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `agence_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`id_emploi`) REFERENCES `emploi` (`id`),
  ADD CONSTRAINT `candidature_ibfk_2` FOREIGN KEY (`id_demandeur`) REFERENCES `demandeur` (`idf`);

--
-- Contraintes pour la table `demandeur`
--
ALTER TABLE `demandeur`
  ADD CONSTRAINT `demandeur_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `emploi`
--
ALTER TABLE `emploi`
  ADD CONSTRAINT `emploi_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id`);

--
-- Contraintes pour la table `statistique`
--
ALTER TABLE `statistique`
  ADD CONSTRAINT `statistique_ibfk_1` FOREIGN KEY (`id_emploi`) REFERENCES `emploi` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
