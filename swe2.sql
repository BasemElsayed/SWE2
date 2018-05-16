-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2018 at 02:46 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swe2`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `brand_name` varchar(255) NOT NULL,
  `brand_category` varchar(255) DEFAULT NULL,
  `brand_description` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`brand_name`, `brand_category`, `brand_description`) VALUES
('ay7aga', 'ay7aga bardo', 'ay7agaaaaaaaaaaaaaaaaa'),
('bnm,.', '', ''),
('', '', ''),
('ZARA\' OR \'1\'=\'1', 'LebS', 'blablalwsdasdjo'),
('ZARA', 'LebS', 'blablalwsdasdjo');

-- --------------------------------------------------------

--
-- Table structure for table `collaborator`
--

CREATE TABLE `collaborator` (
  `id` int(11) NOT NULL,
  `collaboratorid` varchar(255) DEFAULT NULL,
  `storeid` varchar(255) DEFAULT NULL,
  `store_ownerid` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collaborator`
--

INSERT INTO `collaborator` (`id`, `collaboratorid`, `storeid`, `store_ownerid`) VALUES
(206, 'sss2@gmail.com', 'LC', 'sss@gmail.com'),
(36, 'sss@gmail.com', 'macccc', 'sss2@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `first_payment`
--

CREATE TABLE `first_payment` (
  `id` int(11) NOT NULL,
  `storeid` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `first_payment`
--

INSERT INTO `first_payment` (`id`, `storeid`, `userid`) VALUES
(19, 'macccc', 'sss@gmail.com'),
(166, 'LC', 'sss@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(209);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `code` varchar(255) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`code`, `brand`, `description`, `price`, `type`, `product_name`) VALUES
('123', 'fara5batats', 'fara5batats', '123', 'Online', 'fara5batats'),
('123123', 'ay7aga', 'fara5batats', '123', 'Online', 'fara5batats'),
('asdasdasd', 'ay7aga', 'asdasdasd', 'asdasdasd', 'Online', 'asdasdasd'),
('9', 'ay7aga', 'dijq3ekqnwdsm', '5', 'Online', 'vbuwesada');

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `stat_name` varchar(255) NOT NULL,
  `stat_check` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `statistics`
--

INSERT INTO `statistics` (`stat_name`, `stat_check`) VALUES
('Users', 1),
('Products', 0);

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `store_name` varchar(255) NOT NULL,
  `store_address` varchar(255) DEFAULT NULL,
  `store_category` varchar(255) DEFAULT NULL,
  `store_status` varchar(255) DEFAULT NULL,
  `store_type` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`store_name`, `store_address`, `store_category`, `store_status`, `store_type`, `user_email`) VALUES
('macccc', 'ay asd', 'hdoom clothes', '1', 'Onsite', 'sss@gmail.com'),
('LC', 'afsgdhjg', 'qwerwyettu', '1', 'Onsite', 'sss@gmail.com'),
('abo ssss', '', '', '0', 'Online', 'sss@gmail.com'),
('abo nesma', 'efgfdhfyjtuy', '1345263576475', '0', 'Onsite', 'sss2@gmail.com'),
('Joy Kids', 'Emirates', 'le3b atfal', '0', 'Online', 'sss@gmail.com'),
('Joy Kids\' OR \'1\'=\'1', 'Emirates', 'le3b atfal', '0', 'Online', 'sss@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `store_owner_actions`
--

CREATE TABLE `store_owner_actions` (
  `id` int(11) NOT NULL,
  `action_name` varchar(255) DEFAULT NULL,
  `productid` varchar(255) DEFAULT NULL,
  `storeid` varchar(255) DEFAULT NULL,
  `number_of_product` int(11) NOT NULL,
  `number_of_sold_product` int(11) NOT NULL,
  `number_of_visited_product` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `store_owner_actions`
--

INSERT INTO `store_owner_actions` (`id`, `action_name`, `productid`, `storeid`, `number_of_product`, `number_of_sold_product`, `number_of_visited_product`) VALUES
(28, 'Add Product', 'asdasdasd', 'abo ssss', 200, 0, 0),
(199, 'Delete Product', '123', 'LC', 121212, 0, 0),
(38, 'Add Product', '123', 'LC', 1, 0, 0),
(41, 'Edit Product', 'asdasdasd', 'LC', 1, 0, 0),
(42, 'Edit Product', 'asdasdasd', 'LC', 5, 0, 0),
(47, 'Delete Product', 'asdasdasd', 'abo ssss', 200, 0, 3),
(68, 'Add Product', 'asdasdasd', 'LC', 22222, 0, 0),
(69, 'Add Product', 'asdasdasd', 'LC', 22222, 0, 0),
(70, 'Add Product', 'asdasdasd', 'LC', 22222, 0, 0),
(73, 'Add Product', 'asdasdasd', 'LC', 1123, 0, 0),
(74, 'Add Product', 'asdasdasd', 'LC', 1123, 0, 0),
(76, 'Add Product', 'asdasdasd', 'LC', 122, 0, 0),
(78, 'Add Product', 'asdasdasd', 'LC', 1258, 0, 0),
(80, 'Add Product', 'asdasdasd', 'LC', 16666, 0, 0),
(87, 'Add Product', '123', 'abo nesma', 123, 0, 0),
(89, 'Add Product', '123', 'LC', 1222222, 0, 0),
(91, 'Add Product', 'asdasdasd', 'LC', 129999, 0, 0),
(92, 'Delete Product', 'asdasdasd', 'LC', 129999, 0, 0),
(198, 'Add Product', '123', 'LC', 121212, 0, 0),
(173, 'Edit Product', '9', 'LC', 998, 1, 1),
(176, 'Edit Product', '9', 'LC', 998, 1, 1),
(187, 'Edit Product', '9', 'LC', 998, 1, 1),
(200, 'Delete Product', '123', 'LC', 1221106, 1116, 12),
(201, 'Delete Product', '123', 'LC', 20, 0, 0),
(202, 'Delete Product', '123', 'LC', 7, 6, 6),
(204, 'Add Product', '123', 'LC', 121212, 0, 0),
(205, 'Edit Product', '123', 'LC', 121212, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `store_product`
--

CREATE TABLE `store_product` (
  `id` int(11) NOT NULL,
  `number_of_product` int(11) NOT NULL,
  `number_of_sold_product` int(11) NOT NULL,
  `number_of_visited_product` int(11) NOT NULL,
  `prod_store_nameid` varchar(255) DEFAULT NULL,
  `productid` varchar(255) DEFAULT NULL,
  `storeid` varchar(255) DEFAULT NULL,
  `discount` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `store_product`
--

INSERT INTO `store_product` (`id`, `number_of_product`, `number_of_sold_product`, `number_of_visited_product`, `prod_store_nameid`, `productid`, `storeid`, `discount`) VALUES
(203, 121198, 14, 3, '123', '123', 'LC', 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `balance` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `password`, `type`, `user_name`, `balance`) VALUES
('ebasem65@gmail.com', '123', 'Customer', 'BasemElsayed', 8190.620000000001),
('ebasem65@hotmail.com', '123123', 'Customer', '123', 10000),
('bbb@gmail.com', '123', 'Admin', 'basemAdmin', 0),
('sss@gmail.com', '123', 'StoreOwner', 'bbbbbbbb', 11506.1075),
('sss2@gmail.com', '123', 'StoreOwner', 'bbbbbbbbbbbbb2', 14180),
('AlibabaEgypt@gmail.com', '123', 'StoreOwner', 'AlibabaEgypt', 8098),
('x@gmail.com', '123', 'Customer', 'x', 12409);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`brand_name`);

--
-- Indexes for table `collaborator`
--
ALTER TABLE `collaborator`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6i8rdbuq8egmoilwng51jm19q` (`collaboratorid`),
  ADD KEY `FK3h1qp3vgnhyq9bp2qky7s6m59` (`storeid`),
  ADD KEY `FKh18lr33ojxt8ko1d5pgytw8a4` (`store_ownerid`);

--
-- Indexes for table `first_payment`
--
ALTER TABLE `first_payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfftbad2y4csc05gln0pmurrvh` (`storeid`),
  ADD KEY `FKe5ck49habhny6r9wtdy2a48e5` (`userid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`stat_name`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`store_name`),
  ADD KEY `FK6jik1v22ooqjah8ydk33moier` (`user_email`);

--
-- Indexes for table `store_owner_actions`
--
ALTER TABLE `store_owner_actions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbwveuvc6yb9t1bsenly72usuf` (`productid`),
  ADD KEY `FKdn51laaalmnx02ccg1jwxojdv` (`storeid`);

--
-- Indexes for table `store_product`
--
ALTER TABLE `store_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh9vo48ulb4qycbatt0fkhf20p` (`productid`),
  ADD KEY `FKjfv10m3rlu7exx149th2xbiyy` (`storeid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
