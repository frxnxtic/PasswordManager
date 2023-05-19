import com.frxnxtic.passwordmanager.generator.*;
import com.frxnxtic.passwordmanager.encryption.*;
import com.frxnxtic.passwordmanager.ui.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        UserPasswordEncryptor userPasswordEncryptor = new UserPasswordEncryptor();
        String password = PasswordGenerator.generatePassword(12, true, true, true, true);
        String encryptedPassword = userPasswordEncryptor.encryptPassword(password);
        System.out.println(password);
        System.out.println(encryptedPassword);
        boolean matched = userPasswordEncryptor.verifyPassword(password, encryptedPassword);
        System.out.println(matched);
        PasswordsEncryptor encryptor = new PasswordsEncryptor();
        encryptedPassword = encryptor.encryptPassword(password);
        System.out.println(encryptedPassword);
        String decryptedPassword = encryptor.decryptPassword(encryptedPassword);
        System.out.println(decryptedPassword);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}