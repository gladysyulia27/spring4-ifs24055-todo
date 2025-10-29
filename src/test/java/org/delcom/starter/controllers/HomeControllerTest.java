package org.delcom.starter.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerUnitTest {

    @Test
    @DisplayName("Mengembalikan pesan selamat datang yang benar")
    void hello_ShouldReturnWelcomeMessage() {
        HomeController controller = new HomeController();
        String result = controller.hello();
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    @Test
    @DisplayName("Mengembalikan pesan sapaan yang dipersonalisasi")
    void helloWithName_ShouldReturnPersonalizedGreeting() {
        HomeController controller = new HomeController();
        String result = controller.sayHello("Abdullah");
        assertEquals("Hello, Abdullah!", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM yang valid")
    void informasiNim_ShouldReturnValidData() {
        HomeController controller = new HomeController();
        var result = controller.informasiNim("11S24051");

        assertEquals("11S24051", result.get("nim"));
        assertEquals("Sarjana Informatika", result.get("programStudi"));
        assertEquals("2024", result.get("angkatan"));
        assertEquals("51", result.get("urutan"));
    }

    @Test
    @DisplayName("Mengembalikan hasil perolehan nilai yang benar")
    void perolehanNilai_ShouldReturnCorrectStats() {
        HomeController controller = new HomeController();
        var result = controller.perolehanNilai("ODAsOTAsNzA="); // "80,90,70"

        assertEquals("80,90,70", result.get("data"));
        assertEquals(3, result.get("jumlah"));
        assertEquals(240, result.get("total"));
        assertEquals("80.00", result.get("rataRata"));
        assertEquals(90, result.get("nilaiTertinggi"));
        assertEquals(70, result.get("nilaiTerendah"));
    }

    @Test
    @DisplayName("Menghitung jumlah L dan L terbalik dengan benar")
    void perbedaanL_ShouldReturnCorrectDifference() {
        HomeController controller = new HomeController();
        String base64 = Base64.getEncoder().encodeToString("L\nɭ\nL".getBytes());
        var result = controller.perbedaanL(base64);

        assertEquals(2, result.get("jumlahL"));
        assertEquals(1, result.get("jumlahLTerbalik"));
        assertEquals(1, result.get("perbedaan"));
    }

    @Test
    @DisplayName("Mengembalikan kata terbanyak dan tersedikit dengan benar")
    void palingTer_ShouldReturnCorrectWords() {
        HomeController controller = new HomeController();
        String base64 = "c2F5YSBrYW11IGthbXUgZGlhIGRpYSBkaWE="; // "saya kamu kamu dia dia dia"
        var result = controller.palingTer(base64);

        assertEquals("dia", result.get("kataTerbanyak"));
        assertEquals(3, result.get("jumlahTerbanyak"));
        assertEquals("saya", result.get("kataTersedikit"));
        assertEquals(1, result.get("jumlahTersedikit"));
    }
}
