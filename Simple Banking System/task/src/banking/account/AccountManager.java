package banking.account;

import java.util.Random;

public class AccountManager {

    private static final int binNumber = 400000;
    private static long accountSequence = 400000000;

    public static Account newAccount() {

        Random random = new Random(31);

        AccountManager.getCheckSum(binNumber, accountSequence);

        int checkSum = AccountManager.getCheckSum(binNumber, accountSequence);
        int pinCode = random.nextInt(9999 - 1000) + 1000;

        Account account = new AccountImpl(binNumber, accountSequence, checkSum, pinCode);

        AccountManager.accountSequence += 1;

        return account;
    }

    public static int getCheckSum(int binNumber, long accountSequence) {
        String cardNumber = binNumber + "" + accountSequence;
        char[] cardNumbersAsCharArray = cardNumber.toCharArray();

        int[] cardDigits = new int[cardNumbersAsCharArray.length];

        for (int i = 0; i < cardNumbersAsCharArray.length; i++) {

            // populate card digits from cardNumber
            cardDigits[i] = Character.getNumericValue(cardNumbersAsCharArray[i]);

            // multiply the odd numbers with 2
            if (i % 2 == 0) {
                cardDigits[i] = cardDigits[i] * 2;
            }

            // subtract 9, if the number is bigger then 9
            if (cardDigits[i] > 9) {
                cardDigits[i] = cardDigits[i] - 9;
            }
        }

        int sum = 0;
        for (int cardDigit : cardDigits) {
            sum += cardDigit;
        }

        if (sum % 10 == 0) {
            return 0;
        }

        return 10 - (sum % 10);
    }
}
