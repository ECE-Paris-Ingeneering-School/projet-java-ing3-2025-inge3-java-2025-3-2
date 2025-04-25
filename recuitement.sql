-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 25, 2025 at 02:30 PM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recruitment`
--

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

CREATE TABLE `agence` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `secteurActivite` varchar(100) DEFAULT NULL,
  `emailContact` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `nomEntreprise` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`id`, `id_utilisateur`, `secteurActivite`, `emailContact`, `telephone`, `nomEntreprise`) VALUES
(200, 2, 'Recrutement IT', 'contact@buzzcutagency.com', '0601020304', 'Buzzcut Agency');

-- --------------------------------------------------------

--
-- Table structure for table `candidature`
--

CREATE TABLE `candidature` (
  `id` int(11) NOT NULL,
  `id_emploi` int(11) DEFAULT NULL,
  `id_demandeur` int(11) DEFAULT NULL,
  `datePostulation` date DEFAULT NULL,
  `statut` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `candidature`
--

INSERT INTO `candidature` (`id`, `id_emploi`, `id_demandeur`, `datePostulation`, `statut`, `note`) VALUES
(400, 300, 100, '2025-04-25', 'En attente', '4.5'),
(401, 301, 100, '2025-04-25', 'Refusée', '3.8');

-- --------------------------------------------------------

--
-- Table structure for table `demandeur`
--

CREATE TABLE `demandeur` (
  `idf` int(11) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `cv` text,
  `profil` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandeur`
--

INSERT INTO `demandeur` (`idf`, `id`, `cv`, `profil`) VALUES
(100, 1, 'CV de Loan - Dev Web', 'Développeur web passionné par le java.');

-- --------------------------------------------------------

--
-- Table structure for table `emploi`
--

CREATE TABLE `emploi` (
  `id` int(11) NOT NULL,
  `id_agence` int(11) DEFAULT NULL,
  `titre` varchar(100) DEFAULT NULL,
  `description` text,
  `typeContrat` varchar(50) DEFAULT NULL,
  `categorie` varchar(100) DEFAULT NULL,
  `remuneration` float DEFAULT NULL,
  `lieu` varchar(100) DEFAULT NULL,
  `datePublication` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emploi`
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
-- Table structure for table `statistique`
--

CREATE TABLE `statistique` (
  `id` int(11) NOT NULL,
  `id_emploi` int(11) DEFAULT NULL,
  `nombreVue` int(11) DEFAULT NULL,
  `nombrePostulation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `statistique`
--

INSERT INTO `statistique` (`id`, `id_emploi`, `nombreVue`, `nombrePostulation`) VALUES
(500, 300, 120, 15),
(501, 301, 80, 8);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `mail`, `motDePasse`, `type`) VALUES
(1, 'Loan Sournois', 'loan.sournois@mail.com', '123', 'demandeur'),
(2, 'Mathias Buzzcut', 'mathias.buzzcut@mail.com', '123', 'agence');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Indexes for table `candidature`
--
ALTER TABLE `candidature`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_emploi` (`id_emploi`),
  ADD KEY `id_demandeur` (`id_demandeur`);

--
-- Indexes for table `demandeur`
--
ALTER TABLE `demandeur`
  ADD PRIMARY KEY (`idf`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `emploi`
--
ALTER TABLE `emploi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_agence` (`id_agence`);

--
-- Indexes for table `statistique`
--
ALTER TABLE `statistique`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_emploi` (`id_emploi`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `agence_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `candidature`
--
ALTER TABLE `candidature`
  ADD CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`id_emploi`) REFERENCES `emploi` (`id`),
  ADD CONSTRAINT `candidature_ibfk_2` FOREIGN KEY (`id_demandeur`) REFERENCES `demandeur` (`idf`);

--
-- Constraints for table `demandeur`
--
ALTER TABLE `demandeur`
  ADD CONSTRAINT `demandeur_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `emploi`
--
ALTER TABLE `emploi`
  ADD CONSTRAINT `emploi_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id`);

--
-- Constraints for table `statistique`
--
ALTER TABLE `statistique`
  ADD CONSTRAINT `statistique_ibfk_1` FOREIGN KEY (`id_emploi`) REFERENCES `emploi` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
