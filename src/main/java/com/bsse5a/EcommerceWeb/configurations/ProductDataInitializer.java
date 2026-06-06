package com.bsse5a.EcommerceWeb.configurations;

import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.models.enums.GymEquipmentCategories;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import groovy.util.logging.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@lombok.extern.slf4j.Slf4j
@Configuration
@Slf4j
public class ProductDataInitializer {

    @Bean
    public CommandLineRunner initializeProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {

                List<Product> products = List.of(

                        new Product(null,
                                "Chest Press / Shoulder Press Trainer (L301)",
                                "Commercial-grade dual-function chest and shoulder press machine with heavy-duty frame "
                                        + "and smooth resistance mechanism. Ideal for professional gyms.",
                                GymEquipmentCategories.STRENGTH,
                                499_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2025/03/L301-510x510.jpg",
                                4L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "2-Station Home Gym Machine",
                                "Compact multi-functional home gym with dual workout stations. "
                                        + "Supports full-body strength training in limited space.",
                                GymEquipmentCategories.STRENGTH,
                                279_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2022/05/IRHG1800-510x510.jpeg",
                                3L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Gym Exercise Bike (B11)",
                                "High-performance stationary gym bike with adjustable resistance and ergonomic seating. "
                                        + "Designed for endurance, fat loss, and daily cardio.",
                                GymEquipmentCategories.CARDIO,
                                89_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2025/03/B11-510x510.png",
                                12L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Electric Treadmill 6131",
                                "Electric treadmill with shock absorption system and multiple speed levels. "
                                        + "Suitable for home and semi-commercial use.",
                                GymEquipmentCategories.CARDIO,
                                169_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/Treadmill-6131-EAI-510x497.png",
                                6L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "2.00 HP DC Motorized Treadmill (7330CA)",
                                "Powerful 2.00 HP DC motorized treadmill with digital display and foldable design. "
                                        + "Perfect for intensive home cardio workouts.",
                                GymEquipmentCategories.CARDIO,
                                214_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2024/12/2.00-HP-DC-Motorized-Treadmill-Model-7330CA-1.png",
                                5L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "2 in 1 Elliptical Trainer (E51)",
                                "Dual-function elliptical trainer for low-impact cardio and full-body endurance training. "
                                        + "Smooth stride motion reduces joint stress.",
                                GymEquipmentCategories.CARDIO,
                                189_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2022/10/E51-V2-510x510.jpg",
                                4L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Heavy Jump Rope",
                                "Weighted heavy jump rope for explosive cardio, stamina building, and strength conditioning. "
                                        + "Ideal for CrossFit and boxing workouts.",
                                GymEquipmentCategories.ACCESSORIES,
                                4_500.0,
                                "https://advancefitness.pk/wp-content/uploads/2025/08/VF97168-510x510.jpeg",
                                80L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Battle Rope",
                                "Heavy-duty battle rope designed for high-intensity interval training (HIIT). "
                                        + "Improves strength, endurance, and grip power.",
                                GymEquipmentCategories.ACCESSORIES,
                                12_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2025/08/VF95104-3.jpg",
                                25L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Plastic Push-Up Bars",
                                "Durable push-up bars that reduce wrist strain and improve push-up form. "
                                        + "Lightweight and ideal for home workouts.",
                                GymEquipmentCategories.ACCESSORIES,
                                3_200.0,
                                "https://advancefitness.pk/wp-content/uploads/2025/08/VF97722-510x510.jpg",
                                100L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Ankle & Wrist Weights",
                                "Adjustable ankle and wrist weights for increased resistance during workouts. "
                                        + "Suitable for cardio, strength training, and rehabilitation.",
                                GymEquipmentCategories.ACCESSORIES,
                                3_800.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/02-20-510x510.jpg",
                                90L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Latex Resistance Strap",
                                "High-quality latex resistance strap for stretching, mobility, and strength training. "
                                        + "Commonly used in physiotherapy and home workouts.",
                                GymEquipmentCategories.ACCESSORIES,
                                2_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/VF-97601-510x480.jpg",
                                120L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Regular Spring Collars",
                                "Standard spring collars for securing weight plates on bars. "
                                        + "Easy to use and compatible with 1-inch bars.",
                                GymEquipmentCategories.ACCESSORIES,
                                1_500.0,
                                "https://advancefitness.pk/wp-content/uploads/2021/06/IR94028-510x510.jpeg",
                                200L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "7′ Regular One Inch Bar",
                                "Standard 7-foot one-inch bar designed for weightlifting and strength training. "
                                        + "Strong steel construction with balanced grip.",
                                GymEquipmentCategories.STRENGTH,
                                9_500.0,
                                "https://advancefitness.pk/wp-content/uploads/2021/06/IR94001.jpeg",
                                30L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Cast Iron Weight Plate",
                                "High-quality cast iron weight plate suitable for strength training. "
                                        + "Compatible with standard one-inch bars.",
                                GymEquipmentCategories.STRENGTH,
                                2_400.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/1Kg-510x480.jpg",
                                200L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Rubber OP Plate with Steel Rings",
                                "Durable rubber-coated Olympic plate with steel ring insert. "
                                        + "Reduces noise and protects flooring.",
                                GymEquipmentCategories.STRENGTH,
                                7_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2022/04/IR91036-510x510.jpg",
                                80L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Vinyl Kettlebells",
                                "Vinyl-coated kettlebells for functional and strength training. "
                                        + "Comfortable grip and floor-friendly design.",
                                GymEquipmentCategories.STRENGTH,
                                6_500.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/4Kg-1-510x510.jpg",
                                60L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Rubber Hex Dumbbells",
                                "Professional rubber hex dumbbells with anti-roll design. "
                                        + "Perfect for gyms and home workouts.",
                                GymEquipmentCategories.STRENGTH,
                                14_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2020/10/IR-92022-510x510.jpg",
                                40L,
                                LocalDate.now(),
                                LocalDate.now()
                        ),

                        new Product(null,
                                "Olympic Weight Plate Tree",
                                "Heavy-duty Olympic weight plate storage tree. "
                                        + "Keeps gym space organized and clutter-free.",
                                GymEquipmentCategories.STRENGTH,
                                24_900.0,
                                "https://advancefitness.pk/wp-content/uploads/2023/08/IFPTO-01-510x510.webp",
                                15L,
                                LocalDate.now(),
                                LocalDate.now()
                        )
                );

                productRepository.saveAll(products);

                log.info("gym products inserted successfully.");

            }
        };
    }
}
