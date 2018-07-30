package pl.coderslab;

public class Exercise {

    public static String createTabExercise = "CREATE TABLE `exercise` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `title` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";


    public int id;
    public String title;
    public String description;


}
