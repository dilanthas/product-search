

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product_type` varchar(50) NOT NULL,
  `property` varchar(50) NOT NULL,
  `color` varchar(50) ,
  `gb_limit` int(10),
  `price` decimal NOT NULL,
  `street_name` varchar(100) NOT NULL,
  `city_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ;



