package org.delcom.starter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

@GetMapping("/")
public String hello() {
    return "Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!";
}

@GetMapping("/hello/{name}")
public String sayHello(@PathVariable String name) {
    return "Hello, " + name + "!";
}

    // Method 1: Informasi NIM
    @GetMapping("/informasiNim/{nim}")
    public Map<String, String> informasiNim(@PathVariable String nim) {
        Map<String, String> response = new HashMap<>();
        
        if (nim == null || nim.length() < 6) {
            response.put("error", "NIM harus minimal 6 karakter");
            return response;
        }

        try {
            String programStudi = getProgramStudi(nim.substring(0, 3));
            String angkatan = "20" + nim.substring(3, 5);
            String urutan = String.valueOf(Integer.parseInt(nim.substring(5)));

            response.put("nim", nim);
            response.put("programStudi", programStudi);
            response.put("angkatan", angkatan);
            response.put("urutan", urutan);
        } catch (Exception e) {
            response.put("error", "Format NIM tidak valid");
        }

        return response;
    }

    // Method 2: Perolehan Nilai
    @GetMapping("/perolehanNilai")
    public Map<String, Object> perolehanNilai(@RequestParam String strBase64) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String decodedString = new String(Base64.getDecoder().decode(strBase64));
            String[] nilaiArray = decodedString.split(",");
            
            int jumlah = nilaiArray.length;
            int total = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            
            for (String nilaiStr : nilaiArray) {
                int nilai = Integer.parseInt(nilaiStr.trim());
                total += nilai;
                if (nilai > max) max = nilai;
                if (nilai < min) min = nilai;
            }
            
            double rataRata = (double) total / jumlah;
            
            response.put("data", decodedString);
            response.put("jumlah", jumlah);
            response.put("total", total);
            response.put("rataRata", String.format("%.2f", rataRata));
            response.put("nilaiTertinggi", max);
            response.put("nilaiTerendah", min);
            
        } catch (Exception e) {
            response.put("error", "Format Base64 tidak valid");
        }
        
        return response;
    }

    // Method 3: Perbedaan L dan Kebalikannya
    @GetMapping("/perbedaanL")
    public Map<String, Object> perbedaanL(@RequestParam String strBase64) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String decodedString = new String(Base64.getDecoder().decode(strBase64));
            String[] lines = decodedString.split("\n");
            
            int countL = 0;
            int countReverseL = 0;
            
            for (String line : lines) {
                line = line.trim();
                if (line.equals("L")) {
                    countL++;
                } else if (line.equals("ɭ")) { // Unicode untuk L terbalik
                    countReverseL++;
                }
            }
            
            response.put("data", decodedString);
            response.put("jumlahL", countL);
            response.put("jumlahLTerbalik", countReverseL);
            response.put("perbedaan", Math.abs(countL - countReverseL));
            
        } catch (Exception e) {
            response.put("error", "Format Base64 tidak valid");
        }
        
        return response;
    }

    // Method 4: Paling Ter
    @GetMapping("/palingTer")
    public Map<String, Object> palingTer(@RequestParam String strBase64) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String decodedString = new String(Base64.getDecoder().decode(strBase64));
            String[] kataArray = decodedString.toLowerCase().split("\\s+");
            
            Map<String, Integer> kataCount = new HashMap<>();
            String kataTerbanyak = "";
            String kataTersedikit = "";
            int maxCount = 0;
            int minCount = Integer.MAX_VALUE;
            
            for (String kata : kataArray) {
                if (!kata.isEmpty()) {
                    kataCount.put(kata, kataCount.getOrDefault(kata, 0) + 1);
                }
            }
            
            for (Map.Entry<String, Integer> entry : kataCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    kataTerbanyak = entry.getKey();
                }
                if (entry.getValue() < minCount) {
                    minCount = entry.getValue();
                    kataTersedikit = entry.getKey();
                }
            }
            
            response.put("data", decodedString);
            response.put("kataTerbanyak", kataTerbanyak);
            response.put("jumlahTerbanyak", maxCount);
            response.put("kataTersedikit", kataTersedikit);
            response.put("jumlahTersedikit", minCount);
            
        } catch (Exception e) {
            response.put("error", "Format Base64 tidak valid");
        }
        
        return response;
    }

    // Helper method dari kode awal
    private String getProgramStudi(String prefix) {
        switch (prefix) {
            case "11S": return "Sarjana Informatika";
            case "12S": return "Sarjana Sistem Informasi";
            case "14S": return "Sarjana Teknik Elektro";
            case "21S": return "Sarjana Manajemen Rekayasa";
            case "22S": return "Sarjana Teknik Metalurgi";
            case "31S": return "Sarjana Teknik Bioproses";
            case "114": return "Diploma 4 Teknologi Rekasaya Perangkat Lunak";
            case "113": return "Diploma 3 Teknologi Informasi";
            case "133": return "Diploma 3 Teknologi Komputer";
            default: return "Program Studi Tidak Dikenal";
        }
    }
}