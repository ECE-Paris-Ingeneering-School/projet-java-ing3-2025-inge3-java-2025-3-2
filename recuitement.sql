-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 25, 2025 at 02:10 PM
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
