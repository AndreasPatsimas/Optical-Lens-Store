CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `time_insert` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `register_date` datetime NOT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `active` smallint(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `contact_lens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(2) NOT NULL,
  `sph` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `cyl` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `axe` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `bc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `diam` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mk1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mk2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `contact_len_type` varchar(85) COLLATE utf8_bin DEFAULT NULL,
  `register_date` datetime NOT NULL,
  `last_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `glasses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `register_date` datetime NOT NULL,
  `last_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `glasses_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(2) NOT NULL,
  `sph1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `cyl1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ax1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prism1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sph2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `cyl2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ax2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prism2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `dp1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `dp2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `glasses_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_glas_idx` (`glasses_id`),
  CONSTRAINT `fk_glas` FOREIGN KEY (`glasses_id`) REFERENCES `glasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `glasses_lens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(2) NOT NULL,
  `coating` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `color` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `diameter` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `glasses_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_glass1_idx` (`glasses_id`),
  CONSTRAINT `fk_glass1` FOREIGN KEY (`glasses_id`) REFERENCES `glasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `glasses_skeleton` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `model` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `color` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `size` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `nose` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `glasses_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_glasses2_idx` (`glasses_id`),
  CONSTRAINT `fk_glasses2` FOREIGN KEY (`glasses_id`) REFERENCES `glasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `payment_in_advance` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `glasses_id` int(11) DEFAULT NULL,
  `contact_lens_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_idx` (`customer_id`),
  KEY `fk_glasses3_idx` (`glasses_id`),
  KEY `fk_contact_lens_idx` (`contact_lens_id`),
  CONSTRAINT `fk_contact_lens` FOREIGN KEY (`contact_lens_id`) REFERENCES `contact_lens` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_glasses3` FOREIGN KEY (`glasses_id`) REFERENCES `glasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

