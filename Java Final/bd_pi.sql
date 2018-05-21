-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 27 fév. 2018 à 19:28
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_pi`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Discipline` varchar(255) NOT NULL,
  `Date_Event` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Duree` varchar(255) NOT NULL,
  `Lieu` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Affiche` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `adresses_cov`
--

DROP TABLE IF EXISTS `adresses_cov`;
CREATE TABLE IF NOT EXISTS `adresses_cov` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adresses_cov`
--

INSERT INTO `adresses_cov` (`id`, `nom`, `lat`, `lng`) VALUES
(1, 'aaazaaa', 37.07828789310042, 9.91151180585939),
(3, 'yuyuyuy', 36.86059914727882, 10.094188727243136),
(4, 'yuyyyy', 36.69340424160481, 9.800304449899386),
(5, 'Antigonish', 36.8758263, 10.1997022),
(6, 'Vilcú', 36.7665714, 10.1832111),
(7, 'qsdsq', 37.06404338249587, 9.995282557812516),
(8, 'fffff', 36.85994451984648, 10.12025204023439),
(9, 'Antigon', 36.98085230535984, 9.8791753921804),
(10, 'mjez elbeb', 36.723574906341746, 9.57368221601564),
(11, 'dddd', 36.64157634701407, 10.160310157108484),
(12, 'yop', 36.9049810529042, 10.201276210156266),
(13, 'rrrrr', 36.71476853349131, 10.31525936445314);

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `path` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`id`, `libelle`, `description`, `path`) VALUES
(1, 'didier', 'azeaeaz', 'zeeqfcf'),
(2, 'dsvcxvww', 'sdfsdvxsd', 'sxcxw'),
(5, 'zez', 'aze', 'Label');

-- --------------------------------------------------------

--
-- Structure de la table `colocation`
--

DROP TABLE IF EXISTS `colocation`;
CREATE TABLE IF NOT EXISTS `colocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_colocation` varchar(20) DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `place_dispo` int(11) DEFAULT NULL,
  `sexe` varchar(1) DEFAULT NULL,
  `type_maison` varchar(20) DEFAULT NULL,
  `description` text,
  `path` varchar(30) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `colocation`
--

INSERT INTO `colocation` (`id`, `type_colocation`, `adresse`, `prix`, `place_dispo`, `sexe`, `type_maison`, `description`, `path`, `id_user`) VALUES
(1, 'test', 'test', 250, 2, 'f', 'test', 'test description', NULL, NULL),
(2, 'annonce', 'aaa', 12, 1, 'H', 'appartement', '1111', '', NULL),
(3, 'annonce', 'aaa', 12, 1, 'H', 'appartement', '1111', 'event.jpg', NULL),
(4, 'annonce', 'aaa', 12, 1, 'H', 'appartement', '1111', 'event.jpg', NULL),
(5, 'demande', NULL, NULL, 1, 'H', 'appartement', 'hh', NULL, NULL),
(6, 'annonce', 'ooop', 14, 2, 'F', 'sta7', 'lll', 'johnny.jpg', NULL),
(7, 'annonce', 'azeaea', 14, 2, 'F', 'maison', 'hhghggf', '', NULL),
(8, 'annonce', 'aaaa', 7, 1, 'F', 'appartement', 'ppp', '', NULL),
(9, 'annonce', 'fafafafaf', 12, 1, 'F', 'sta7', 'nnnn', 'johnny.jpg', 6),
(10, 'demande', NULL, NULL, 3, 'H', 'studio', 'ooo', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_document` int(11) DEFAULT NULL,
  `id_collocation` int(11) DEFAULT NULL,
  `id_covoiturage` int(11) DEFAULT NULL,
  `ID_Evenement` int(11) DEFAULT NULL,
  `id_topic` int(11) DEFAULT NULL,
  `Post` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Contenu` varchar(255) NOT NULL,
  `Etat_Commentaire` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_id` (`id_document`),
  KEY `fk_collocation_id` (`id_collocation`),
  KEY `fk_covoiturage_id` (`id_covoiturage`),
  KEY `fk_event_id` (`ID_Evenement`),
  KEY `id_user` (`id_user`),
  KEY `id_reclamation` (`id_topic`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_document`, `id_collocation`, `id_covoiturage`, `ID_Evenement`, `id_topic`, `Post`, `Contenu`, `Etat_Commentaire`, `id_user`, `rating`) VALUES
(2, NULL, NULL, NULL, 12, NULL, '2018-02-16 15:49:58', 'TestCommEv1', 'OK', NULL, NULL),
(3, NULL, NULL, NULL, 12, NULL, '2018-02-16 15:49:58', 'TestCommEv2', 'OK', NULL, NULL),
(4, NULL, NULL, NULL, 2, NULL, '2018-02-16 19:11:39', 'TestCommEv0', 'OK', NULL, NULL),
(5, NULL, NULL, NULL, 2, NULL, '2018-02-16 20:28:28', 'TestCommEv0', 'OK', NULL, NULL),
(6, NULL, NULL, NULL, 2, NULL, '2018-02-16 21:22:15', 'TestCommEv0', 'OK', NULL, NULL),
(7, NULL, NULL, NULL, 2, NULL, '2018-02-16 21:23:26', 'TestCommEv0', 'OK', NULL, NULL),
(8, NULL, NULL, NULL, 13, NULL, '2018-02-17 22:15:11', 'heey test', 'OK', NULL, NULL),
(10, 27, NULL, NULL, NULL, NULL, '2018-02-27 17:11:38', 'commentaire', NULL, 6, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `covoiturage`
--

DROP TABLE IF EXISTS `covoiturage`;
CREATE TABLE IF NOT EXISTS `covoiturage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart` varchar(100) NOT NULL,
  `destination` varchar(100) NOT NULL,
  `id_u` int(11) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `etat` tinyint(4) NOT NULL,
  `date` date NOT NULL,
  `vue` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_u` (`id_u`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `covoiturage`
--

INSERT INTO `covoiturage` (`id`, `depart`, `destination`, `id_u`, `type`, `etat`, `date`, `vue`) VALUES
(1, 'Bangus Kulon', 'Yanwang', 5, 1, 0, '2018-02-14', 1),
(2, 'Antigonish', 'Vilcú', 2, 1, 0, '2018-02-02', 1),
(4, 'Santa Inês', 'Futian', 4, 1, 1, '2018-09-01', 1),
(46, 'svvxcv', 'wxcwx', 1, 0, 1, '2018-02-17', 1),
(47, 'esprit', 'ghazela', 1, 1, 1, '2018-02-20', 1),
(48, 'hgg', 'hhhh', 1, 1, 1, '2018-02-17', 1),
(49, 'qsdsq', 'fffff', 1, 0, 1, '2018-02-17', 1),
(50, 'cwxc', 'wxcw', 1, 0, 1, '2018-02-17', 1),
(51, 'esprit', 'sousse', 1, 0, 1, '2018-02-17', 1),
(52, 'tounes', 'sfax', 5, 1, 1, '2018-02-22', 1),
(53, 'esprit', 'tunis', 1, 0, 1, '2018-02-21', 1),
(55, 'sss', 'dddd', 4, 0, 1, '2018-02-25', 1),
(57, 'yuyuyuy', 'yuyyyy', 4, 0, 1, '2018-02-25', 1),
(59, 'Antigon', 'mjez elbeb', 4, 0, 1, '2018-02-25', 1),
(60, 'dddd', 'mjez elbeb', 4, 0, 1, '2018-02-25', 1),
(61, 'yop', 'Antigonish', 4, 0, 1, '2018-02-25', 1),
(62, 'Antigon', 'mjez elbeb', 4, 1, 1, '2018-02-27', 1),
(63, 'aaazaaa', 'rrrrr', 6, 1, 1, '2018-02-28', 1);

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `path` varchar(50) DEFAULT NULL,
  `niveau` varchar(11) DEFAULT NULL,
  `matiere` varchar(50) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`id`, `titre`, `description`, `path`, `niveau`, `matiere`, `etat`, `id_user`) VALUES
(27, 'doc 2', 'description doc 2', 'EIGRP.pdf', '3A', 'java', 'publique', 4),
(28, 'doc3', 'description doc 3', 'atelier2- CRUD-PHP.pdf', '3B', 'uml', 'publique', 5),
(30, 'doc 4', 'description doc 4', 'atelier2- CRUD-PHP.pdf', '3B', 'C++', 'publique', NULL),
(31, 'doc 6', 'description doc 6', 'Cours PHP5 (1).pdf', '3B', 'c#', 'en attente', NULL),
(32, 'aaa', 'aaaa', 'OSPF.pdf', '3A', 'tttt', 'publique', 6);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `ID_Evaluation` int(10) NOT NULL AUTO_INCREMENT,
  `ID_Evenement` int(10) NOT NULL,
  `ID_User` int(10) NOT NULL,
  `Note` int(10) DEFAULT NULL,
  `ID_Commentaire` int(10) DEFAULT NULL,
  `Etat_Commentaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Evaluation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ID_Organisateur` int(50) NOT NULL DEFAULT '0',
  `Nom` varchar(255) NOT NULL,
  `Type` varchar(255) NOT NULL,
  `Type_Reservation` varchar(255) NOT NULL,
  `Date_Event` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Duree` varchar(255) NOT NULL,
  `Lieu` varchar(255) NOT NULL,
  `Nombre` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Affiche` varchar(255) NOT NULL,
  `Etat` varchar(255) NOT NULL,
  `Prix` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`ID`, `ID_Organisateur`, `Nom`, `Type`, `Type_Reservation`, `Date_Event`, `Duree`, `Lieu`, `Nombre`, `Description`, `Affiche`, `Etat`, `Prix`) VALUES
(2, 1, 'Event0', 'Autres', 'Gratuite', '2018-02-16 15:01:59', '2h', 'Esprit', 50, 'Event00', 'title.png', 'Archivé', 0),
(3, 1, 'Event0', 'Autres', 'Gratuite', '2018-02-16 14:48:12', '2h', 'Esprit', 50, 'des', 'title.png', 'Oui', 0),
(4, 4, 'Event0', 'Autres', 'Gratuite', '2018-02-16 14:55:24', '2h', 'Esprit', 50, 'Event00', 'title.png', 'Oui', 0),
(12, 3, 'Eve1', 'Autres', 'Gratuite', '2018-02-16 15:46:52', '2h', 'Esprit', 50, 'des', 'title.png', 'Oui', 0),
(13, 0, 'hello event', 'Autres', 'Gratuite', '2018-02-17 22:17:14', '3h', 'C2', 10, 'hello test', 'add.png', 'Oui', 0);

-- --------------------------------------------------------

--
-- Structure de la table `favoris_colocation`
--

DROP TABLE IF EXISTS `favoris_colocation`;
CREATE TABLE IF NOT EXISTS `favoris_colocation` (
  `id_fav` int(11) NOT NULL AUTO_INCREMENT,
  `id_colocation` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_fav`),
  KEY `id_colocation` (`id_colocation`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `favoris_colocation`
--

INSERT INTO `favoris_colocation` (`id_fav`, `id_colocation`, `id_user`) VALUES
(1, 2, 1),
(2, 4, 1),
(3, 2, 6),
(4, 6, 6);

-- --------------------------------------------------------

--
-- Structure de la table `galerie`
--

DROP TABLE IF EXISTS `galerie`;
CREATE TABLE IF NOT EXISTS `galerie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objet` varchar(30) NOT NULL,
  `description` longtext NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `objet`, `description`, `date`) VALUES
(1, 'sdfswc', 'fsqdsq', NULL),
(5, 'bbbb', 'hhhh', NULL),
(7, 'ytytyt', 'uiuiui', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `ID_Reservation` int(10) NOT NULL AUTO_INCREMENT,
  `ID_Evenement` int(10) NOT NULL,
  `ID_Participant` int(10) NOT NULL,
  `Type_Reservation` varchar(255) NOT NULL,
  `Tarif` float DEFAULT NULL,
  `Numero_Ticket` varchar(255) DEFAULT NULL,
  `Etat` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Reservation`),
  KEY `fk_event_res_id` (`ID_Evenement`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`ID_Reservation`, `ID_Evenement`, `ID_Participant`, `Type_Reservation`, `Tarif`, `Numero_Ticket`, `Etat`) VALUES
(1, 2, 1, 'Gratuite', NULL, NULL, 'Confirmé'),
(2, 2, 2, 'Gratuite', NULL, NULL, 'Annulé'),
(3, 2, 2, 'Gratuite', NULL, NULL, 'Confirmé');

-- --------------------------------------------------------

--
-- Structure de la table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `topic`
--

INSERT INTO `topic` (`id`, `sujet`, `description`) VALUES
(1, 'sauce', 'dfgdsvsqd'),
(2, 'eeeeer', 'reeeeeeeeeee'),
(6, 'belbelbleble', 'bababababba'),
(7, 'ye lili', 'ye lilaa'),
(14, 'yuyuy', 'iooio');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `expired` tinyint(1) DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) DEFAULT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `niveau` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`,`username_canonical`,`email`,`email_canonical`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `niveau`, `photo`) VALUES
(1, 'Oussama', 'Oussama', 'Oussama@Oussama.tn', 'Oussama@Oussama.tn', 0, 'hjhh', '123456789', '2018-02-20 00:00:00', 0, 0, '2018-02-22 00:00:00', 'kkk', '2018-02-12 00:00:00', 'dzad', 0, '2018-02-22 00:00:00', '2A', 'classic.png'),
(2, 'Aymen', 'Aymen', 'Aymen@Aymen.tn', 'Aymen@Aymen.tn', 0, 'pp', '123456789', '2018-02-07 00:00:00', 0, 0, '2018-02-04 00:00:00', 'hj', '2018-02-16 00:00:00', 'ghjg', 0, '2018-02-27 00:00:00', 'nnn', 'classic.png'),
(4, 'Manai', 'Manai', 'mohamedselim.manai@esprit.tn', 'mohamedselim.manai@esprit.tn', NULL, NULL, '$2y$10$ZaPYWptrW9XuSbKrAcwlFOUMK1hv7rZyyJpuY1wuGmwLUWOOvope2', NULL, NULL, NULL, NULL, NULL, NULL, 'etudiant', NULL, NULL, '3A', 'classic.png'),
(5, 'ManaiSelim', 'ManaiSelim', 'manai.mohamed.selim@gmail.com', 'manai.mohamed.selim@gmail.com', NULL, NULL, '$2a$13$kzOOWKjGQOBU4YCZ2NoBre0AhWfF7RPVBcLacZxnsqQeicDSlwq7W', NULL, NULL, NULL, NULL, NULL, NULL, 'etudiant', NULL, NULL, '3A', 'classic.png'),
(6, 'Selim', 'Selim', 'selim.mattar@esprit.tn', 'selim.mattar@esprit.tn', NULL, NULL, '$2a$13$LI31eRER/HQI5Pye5u82c.szOhreITCoGO7NZKNdyPD4cz2g8i0sa', NULL, NULL, NULL, NULL, NULL, NULL, 'étudiant', NULL, NULL, '4A', 'johnny.jpg');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `colocation`
--
ALTER TABLE `colocation`
  ADD CONSTRAINT `colocation_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`id_document`) REFERENCES `document` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaire_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `covoiturage`
--
ALTER TABLE `covoiturage`
  ADD CONSTRAINT `covoiturage_ibfk_1` FOREIGN KEY (`id_u`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `favoris_colocation`
--
ALTER TABLE `favoris_colocation`
  ADD CONSTRAINT `favoris_colocation_ibfk_1` FOREIGN KEY (`id_colocation`) REFERENCES `colocation` (`id`),
  ADD CONSTRAINT `favoris_colocation_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
