package pl.coderslab;

import java.text.DateFormat;

public class Solution {

    public static String createTabSolution = "CREATE TABLE `solution` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `created` datetime DEFAULT NULL,\n" +
            "  `updated` datetime DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  `exercise_id` int(11) DEFAULT NULL,\n" +
            "  `user_id` bigint(20) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  KEY `fk_solution_1_idx` (`user_id`),\n" +
            "  KEY `fk_solution_2_idx` (`exercise_id`),\n" +
            "  CONSTRAINT `fk_solution_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `fk_solution_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";

    public int id;
    public DateFormat created;
    public DateFormat updated;
    public String description;
    public int exerciseId;
    public int userId;


}
