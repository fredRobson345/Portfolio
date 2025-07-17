#include <SPI.h>
#include <MFRC522.h>
#include <IRremote.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

// Pin Definitions
#define RST_PIN 9       // Reset pin for RC522
#define SS_PIN 10       // Slave Select pin for RC522
#define LED_LOCKED 6    // "Locked" LED
#define LED_UNLOCKED 7  // "Unlocked" LED
#define BUZZER_PIN 8    // Buzzer pin
#define IR_RECEIVER_PIN 3 // IR receiver pin

// Initialize RFID, IR receiver, and LCD
MFRC522 mfrc522(SS_PIN, RST_PIN);
IRrecv irrecv(IR_RECEIVER_PIN);
decode_results results;
LiquidCrystal_I2C lcd(0x27, 16, 2); // Adjust I2C address if necessary

// Passwords stored in code
const char* passwords[] = {
  "1234", "5678", "91011", "1213", "1415", "1617", "1819", "2021", "2223"
};
int selectedPasswordIndex = 0; // Initially select the first password
bool isLocked = true; // Initial lock state

// Registered RFID UID (replace with your tag's UID)
byte allowedUID[] = {0xAB, 0xCD, 0xEF, 0x12}; // Example UID (replace with actual)

void setup() {
  // Initialize serial, RFID, IR, and LCD
  Serial.begin(9600);
  SPI.begin();
  mfrc522.PCD_Init();
  irrecv.enableIRIn();
  lcd.init();
  lcd.backlight();

  // Initialize LEDs and buzzer
  pinMode(LED_LOCKED, OUTPUT);
  pinMode(LED_UNLOCKED, OUTPUT);
  pinMode(BUZZER_PIN, OUTPUT);
  digitalWrite(LED_LOCKED, HIGH);
  digitalWrite(LED_UNLOCKED, LOW);

  // Display initial status
  lcd.setCursor(0, 0);
  lcd.print("System Locked");
  lcd.setCursor(0, 1);
  lcd.print("Pass: ");
  lcd.print(passwords[selectedPasswordIndex]);
  Serial.println("Place your RFID card near the reader...");
}

void loop() {
  // RFID Card Detection
  if (mfrc522.PICC_IsNewCardPresent() && mfrc522.PICC_ReadCardSerial()) {
    if (isAllowedUID()) {
      toggleLockState();
      signalChange();
    } else {
      Serial.println("Unauthorized card!");
      tone(BUZZER_PIN, 500, 300);
    }
    mfrc522.PICC_HaltA();
  }

  // IR Remote Input Detection
  if (irrecv.decode(&results)) {
    handleIRInput(results.value);
    irrecv.resume(); // Receive the next value
  }
}

bool isAllowedUID() {
  if (mfrc522.uid.size != sizeof(allowedUID)) {
    return false;
  }
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    if (mfrc522.uid.uidByte[i] != allowedUID[i]) {
      return false;
    }
  }
  return true;
}

void toggleLockState() {
  if (isLocked) {
    digitalWrite(LED_LOCKED, LOW);
    digitalWrite(LED_UNLOCKED, HIGH);
    lcd.setCursor(0, 0);
    lcd.print("System Unlocked");
    Serial.println("Files Unlocked");
  } else {
    digitalWrite(LED_LOCKED, HIGH);
    digitalWrite(LED_UNLOCKED, LOW);
    lcd.setCursor(0, 0);
    lcd.print("System Locked  ");
    Serial.println("Files Locked");
  }
  isLocked = !isLocked;
}

void signalChange() {
  tone(BUZZER_PIN, 1000, 200);
}

void handleIRInput(unsigned long input) {
  switch (input) {
    case 0xFFA25D: // Button "1"
    case 0xFF629D: // Button "2"
    case 0xFFE21D: // Button "3"
    case 0xFF22DD: // Button "4"
    case 0xFF02FD: // Button "5"
    case 0xFFC23D: // Button "6"
    case 0xFFE01F: // Button "7"
    case 0xFFA857: // Button "8"
    case 0xFF906F: // Button "9"
      selectedPasswordIndex = (input - 0xFFA25D) / 0x1000; // Map IR code to 0-8
      updateLCD();
      break;
    case 0xFF6897: // Lock/Unlock button (e.g., "0" button)
      toggleLockState();
      break;
    default:
      Serial.println("Unknown button");
  }
}

void updateLCD() {
  lcd.setCursor(0, 1);
  lcd.print("Pass: ");
  lcd.print(passwords[selectedPasswordIndex]);
}
