package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api")
public class DataTestController {


    // Endpoint for grade
    @GetMapping("/grade")
    public ResponseEntity<GradeResponse> grade(@RequestParam int averageGrade) {
        char grade;
        if (averageGrade >= 90 && averageGrade <= 100) {
            grade = 'A';
        } else if (averageGrade >= 80) {
            grade = 'B';
        } else if (averageGrade >= 70) {
            grade = 'C';
        } else if (averageGrade >= 60) {
            grade = 'D';
        } else if (averageGrade >= 0) {
            grade = 'F';
        } else {
            grade = 'I'; // Invalid
        }
        return ResponseEntity.ok(new GradeResponse(grade));
    }

    public static class GradeResponse {
        private char grade;

        public GradeResponse(char grade) {
            this.grade = grade;
        }

        public char getGrade() {
            return grade;
        }

        public void setGrade(char grade) {
            this.grade = grade;
        }
    }

    // Endpoint for getFare
    @GetMapping("/fare")
    public ResponseEntity<FareResponse> getFare(@RequestParam int age, @RequestParam int distance) {
        int fare = 0;

        if (age >= 4 && age <= 14) {
            fare = (distance >= 10) ? 130 : 100;
        } else if (age >= 15) {
            if (distance < 10 && age >= 60) {
                fare = 160;
            } else if (distance >= 10 && age < 60) {
                fare = 250;
            } else {
                fare = 200;
            }
        }

        return ResponseEntity.ok(new FareResponse(fare));
    }

    public static class FareResponse {
        private int fare;

        public FareResponse(int fare) {
            this.fare = fare;
        }

        public int getFare() {
            return fare;
        }

        public void setFare(int fare) {
            this.fare = fare;
        }
    }




    // Endpoint for calculateTax
    @GetMapping("/calculateTax")
    public ResponseEntity<TaxResponse> calculateTax(@RequestParam double income, @RequestParam int dependents) {
        double tax = (income >= 50000)
                ? (dependents >= 2 ? income * 0.2 : income * 0.25)
                : (dependents >= 2 ? income * 0.1 : income * 0.15);

        // Trả về đối tượng TaxResponse dưới dạng JSON
        return ResponseEntity.ok(new TaxResponse(tax));
    }
    public class TaxResponse {
        private double tax;

        // Constructor
        public TaxResponse(double tax) {
            this.tax = tax;
        }

        // Getter and Setter
        public double getTax() {
            return tax;
        }

        public void setTax(double tax) {
            this.tax = tax;
        }
    }



    // Endpoint for FinalPriceCalculate
    @GetMapping("/FinalPriceCalculate")
    public ResponseEntity<FinalPriceResponse> FinalPriceCalculate(@RequestParam double basePrice, @RequestParam int couponCode) {
        double finalPrice;

        // Logic tính giá dựa trên couponCode
        if (couponCode >= 1 && couponCode <= 3) {
            finalPrice = basePrice * 0.95; // Coupon giảm 5%
        } else if (couponCode >= 4 && couponCode <= 6) {
            finalPrice = basePrice * 0.8;  // Coupon giảm 20%
        } else {
            finalPrice = basePrice;       // Không giảm giá
        }

        // Trả về đối tượng FinalPriceResponse dưới dạng JSON
        return ResponseEntity.ok(new FinalPriceResponse(finalPrice));
    }
    class FinalPriceResponse {
        private double finalPrice;

        // Constructor
        public FinalPriceResponse(double finalPrice) {
            this.finalPrice = finalPrice;
        }

        // Getter and Setter
        public double getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(double finalPrice) {
            this.finalPrice = finalPrice;
        }
    }

    // Endpoint for determineExamResult


        @GetMapping("/determineExamResult")
        public ResponseEntity<Map<String, String>> determineExamResult(@RequestParam int score) {
            int passMark = 80; // Pass mark cố định

            // Kiểm tra nếu score vượt quá 100
            if (score > 100) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Score cannot exceed 100.");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            String result = (score >= passMark) ? "Pass" : "Fail";

            Map<String, String> response = new HashMap<>();
            response.put("result", result);
            return ResponseEntity.ok(response);
        }



    // Endpoint for lateBonusCalcu
    @GetMapping("/lateBonusCalcu")
    public ResponseEntity<BonusResponse> lateBonusCalcu(@RequestParam double salary, @RequestParam int years) {
        // Tính toán bonus dựa trên số năm
        double bonus = (years >= 10) ? salary * 0.1 : (years >= 5 ? salary * 0.05 : 0.0);

        // Trả về đối tượng BonusResponse dưới dạng JSON
        return ResponseEntity.ok(new BonusResponse(bonus));
    }
    class BonusResponse {
        private double bonus;

        // Constructor
        public BonusResponse(double bonus) {
            this.bonus = bonus;
        }

        // Getter and Setter
        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }
    }


    @GetMapping("/MathEnglishGradeCalculator")
    public ResponseEntity<GradeResponse1> MathEnglishGradeCalculator(@RequestParam int math, @RequestParam int english) {
        char grade;

        // Nếu cả điểm Toán và Tiếng Anh đều trên 90, xếp loại A
        if (math >= 90 && english >= 90) {
            grade = 'A';
        } else if (math >= 50 && english >= 60) {
            grade = (math >= 80 || english >= 90) ? 'B' : 'C';
        } else {
            grade = 'D';
        }

        // Trả về đối tượng GradeResponse dưới dạng JSON
        return ResponseEntity.ok(new GradeResponse1(grade));
    }
    class GradeResponse1 {
        private char grade;

        // Constructor
        public GradeResponse1(char grade) {
            this.grade = grade;
        }

        // Getter and Setter
        public char getGrade() {
            return grade;
        }

        public void setGrade(char grade) {
            this.grade = grade;
        }
    }


    // Endpoint for getShippingCost
    // Endpoint for getShippingCost
    @GetMapping("/getShippingCost")
    public ResponseEntity<ShippingCostResponse> getShippingCost(@RequestParam double weight, @RequestParam int distance) {
        double cost = (weight >= 20)
                ? (distance >= 100 ? 50.0 : 30.0)
                : (distance >= 100 ? 20.0 : 10.0);

        // Trả về đối tượng ShippingCostResponse dưới dạng JSON
        return ResponseEntity.ok(new ShippingCostResponse(cost));
    }
    public class ShippingCostResponse {
        private double cost;

        // Constructor
        public ShippingCostResponse(double cost) {
            this.cost = cost;
        }

        // Getter and Setter
        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }

    public class ShippingFareResponse {

        private double fare;  // Phí vận chuyển

        // Constructor
        public ShippingFareResponse(double fare) {
            this.fare = fare;
        }

        // Getter và Setter
        public double getFare() {
            return fare;
        }

        public void setFare(double fare) {
            this.fare = fare;
        }
    }
    @GetMapping("/shippingFare")
    public ResponseEntity<ShippingFareResponse> getShippingFare(
            @RequestParam double weight,  // Trọng lượng gói hàng
            @RequestParam int distance  // Khoảng cách giao hàng
    ) {  // Loại hình giao hàng (ví dụ: nhanh, tiêu chuẩn)

        double fare = 0;

        // Tính giá vé dựa trên trọng lượng gói hàng
        if (weight <= 5) {
            fare = 50;  // Gói hàng dưới 5kg có mức giá cố định
        } else if (weight >= 5 && weight <= 20) {
            fare = 100;  // Gói hàng từ 5kg đến 20kg
        } else {
            fare = 150;  // Gói hàng trên 20kg
        }

        // Điều chỉnh giá vé theo khoảng cách
        if (distance >= 100) {
            fare = fare + 50;  // Nếu khoảng cách trên 100km, tăng phí giao hàng
        }

        return ResponseEntity.ok(new ShippingFareResponse(fare));

    }
    @GetMapping("/CustomerDiscount")
    public ResponseEntity<DiscountResponse> customerDiscount(@RequestParam int years, @RequestParam double annualSpend) {
        double discount;

        // Nếu số năm sử dụng dịch vụ < 1, không có giảm giá
        if (years <= 1) {
            discount = 0.0;
        } else {
            // Nếu số năm sử dụng dịch vụ >= 1, kiểm tra mức chi tiêu hàng năm
            if (annualSpend >= 1000) {
                discount = (years >= 5) ? 0.20 : 0.15; // Nếu chi tiêu > 1000, giảm 20% nếu dùng dịch vụ > 5 năm, giảm 15% nếu < 5 năm
            } else if (annualSpend >= 500) {
                discount = 0.10;
                // Nếu chi tiêu > 500, giảm 10%
            } else {
                discount = 0.05; // Nếu chi tiêu <= 500, giảm 5%
            }
        }

        // Trả về đối tượng JSON
        return ResponseEntity.ok(new DiscountResponse(discount));
    }
    // Lớp để định nghĩa phản hồi dạng JSON
    class DiscountResponse {
        private double discount;

        public DiscountResponse(double discount) {
            this.discount = discount;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }
    }
    @GetMapping("/OrderStatus")
    public ResponseEntity<String> orderStatus(@RequestParam double orderValue, @RequestParam int shippingStatus) {
        String status;

        // Nếu giá trị đơn hàng < 100, trả về "Low Value"
        if (orderValue < 100) {
            status = "Low Value";
        } else {
            // Kiểm tra trạng thái vận chuyển bằng mã số
            switch (shippingStatus) {
                case 1:
                    status = "Delivered";
                    break;
                case 2:
                    status = "In Transit";
                    break;
                case 3:
                    status = "Pending";
                    break;
                default:
                    status = "Unknown Status";
                    break;
            }
        }

        return ResponseEntity.ok(status);
    }

    // Lớp để định nghĩa phản hồi dạng JSON



//    Final List of 15 Endpoints:
// /grade: Xác định hạng điểm dựa trên điểm trung bình. => ok
// /fare: Tính giá vé dựa trên tuổi và khoảng cách. => ok
// /check-valid-date: Kiểm tra tính hợp lệ của ngày tháng.
// /BMICalculator: Tính chỉ số BMI.
// /GCDCalculator: Tìm ước chung lớn nhất (GCD) của hai số.
// /calculateTax: Tính thuế dựa trên thu nhập và số người phụ thuộc.
// /getTravelTime: Tính thời gian di chuyển dựa trên quãng đường và tốc độ.
// /FinalPriceCalculate: Tính giá cuối cùng sau khi áp dụng mã giảm giá.
// /determineExamResult: Xác định kết quả kỳ thi (Đậu/Rớt).
// /lateBonusCalcu: Tính tiền thưởng trễ hạn dựa trên lương và số năm làm việc.
// /MathEnglishGradeCalculator: Xác định hạng điểm dựa trên điểm Toán và Tiếng Anh.
// /getShippingCost: Tính phí vận chuyển dựa trên trọng lượng và khoảng cách.
// /power: Tính lũy thừa của một số.
// /isTriangle: Kiểm tra xem ba cạnh có tạo thành tam giác hay không.
// /calculateZodiac: Kiểm tra logic từng khoảng ngày/tháng tương ứng với các cung hoàng đạo.
}

