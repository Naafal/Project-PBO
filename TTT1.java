import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;

class TTT1 extends JFrame implements ItemListener, ActionListener {
    int i, j, ii, jj, x, y, yesnull;
    int a[][] = { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
            { 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };
    int a1[][] = { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
            { 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };

    boolean state, type, set;

    Icon ic1, ic2, icon, ic11, ic22;
    Checkbox c1, c2;
    JLabel l1, l2;
    JButton b[] = new JButton[9];
    JButton reset;
    JButton playButton;
    JPanel playPanel, choicePanel, gamePanel;
    CardLayout cardLayout;
    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    JButton mainMenu;


    public void showButton() {
        x = 10;
        y = 10;
        j = 0;
        for (i = 0; i <= 8; i++, x += 100, j++) {
            b[i] = new JButton();
            if (j == 3) {
                j = 0;
                y += 100;
                x = 10;
            }
            b[i].setBounds(x, y, 100, 100);
            gamePanel.add(b[i]);
            b[i].addActionListener(this);
        } // eof for

        JButton mainMenu = new JButton("Menu Utama");
        mainMenu.setBounds(100, 350, 100, 50);
        gamePanel.add(mainMenu);
        reset.addActionListener(this);
    } // eof showButton

    public void check(int num1) {
        for (ii = 0; ii <= 7; ii++) {
            for (jj = 1; jj <= 3; jj++) {
                if (a[ii][jj] == num1) {
                    a[ii][4] = 11;
                }
            } // eof for jj
        } // eof for ii
    } // eof check

    public void complogic(int num) {
        for (i = 0; i <= 7; i++) {
            set = true;
            if (a[i][4] == 10) {
                int count = 0;
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() != null) {
                        count++;
                    } else {
                        yesnull = a[i][j];
                    }
                }
                if (count == 2) {
                    b[yesnull - 1].setIcon(ic2);
                    this.check(yesnull);
                    set = false;
                    break;
                }
            } else if (a[i][0] == 10) {
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() == null) {
                        b[(a[i][j] - 1)].setIcon(ic2);
                        this.check(a[i][j]);
                        set = false;
                        break;
                    }
                }
                if (!set)
                    break;
            }
            if (!set)
                break;
        }
    }

    TTT1() {
        super("tic tac toe by ashwani");

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Tampilan ke-1 (Play Button)
        playPanel = new JPanel();
        playButton = new JButton("Mainkan");
        playButton.addActionListener(this);
        playPanel.setLayout(new GridBagLayout()); // Menggunakan GridBagLayout
        playPanel.add(playButton, new GridBagConstraints());

// Menetapkan properti agar tombol berada di tengah
        playButton.setPreferredSize(new Dimension(100, 50)); // Mengatur ukuran sesuai dengan tombol reset
        playButton.setHorizontalAlignment(JButton.CENTER);
        playButton.setVerticalAlignment(JButton.CENTER);

        // Tampilan ke-2 (Pilihan vs Computer vs Friend)
        choicePanel = new JPanel(new GridBagLayout());

        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("vs computer", cbg, false);
        c2 = new Checkbox("vs friend", cbg, false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Atur padding agar berada di tengah atas
        choicePanel.add(c1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); // Atur padding agar berada di tengah bawah
        choicePanel.add(c2, gbc);

        c1.addItemListener(this);
        c2.addItemListener(this);


        // Tampilan ke-3 (Game Panel)
        gamePanel = new JPanel();
        gamePanel.setLayout(null);

        // Tambahkan label untuk skor player1
        l1 = new JLabel("Player1: " + scorePlayer1);
        l1.setBounds(10, 350, 150, 50);
        gamePanel.add(l1);

        // Tambahkan label untuk skor player2 atau komputer tergantung mode
        // Tambahkan label untuk skor player2 atau komputer tergantung mode
        l2 = new JLabel(type ? "Player2: " + scorePlayer2 : "Computer: " + scorePlayer2);
        l2.setBounds(220, 350, 150, 50);
        gamePanel.add(l2);


        // Tambahkan Tampilan ke-1, ke-2, dan ke-3 ke frame
        add(playPanel, "play");
        add(choicePanel, "choice");
        add(gamePanel, "game");

        state = true;
        type = true;
        set = true;
        ic1 = new ImageIcon("resources/ic1.jpg");
        ic2 = new ImageIcon("resources/ic2.jpg");
        ic11 = new ImageIcon("resources/ic11.jpg");
        ic22 = new ImageIcon("resources/ic22.jpg");

        setSize(330, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {
        if (c1.getState()) {
            type = false;
        } else if (c2.getState()) {
            type = true;
        }

        // Tambahkan logika berikut
        if (type) {
            l2.setText("Player2: " + scorePlayer2);
        } else {
            l2.setText("Computer: " + scorePlayer2);
        }

        cardLayout.show(getContentPane(), "game");
        remove(playPanel);
        remove(choicePanel);
        repaint();
        showButton();
    }


    // ...

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            // Pindah ke Tampilan ke-2 (Pilihan vs Computer vs Friend)
            cardLayout.show(getContentPane(), "choice");
        } else if (e.getSource() == mainMenu) {
            // Pindah ke Tampilan ke-1 (Play Button)
            cardLayout.show(getContentPane(), "play");
        }else {
            if (type == true) { // logicfriend
                if (e.getSource() == reset) {
                    for (i = 0; i <= 8; i++) {
                        if (b[i] != null) {
                            b[i].setIcon(null);
                        }
                    }
                } else {
                    for (i = 0; i <= 8; i++) {
                        if (b[i] != null && e.getSource() == b[i]) {
                            if (b[i].getIcon() == null) {
                                if (state == true) {
                                    icon = ic2;
                                    state = false;
                                } else {
                                    icon = ic1;
                                    state = true;
                                }
                                b[i].setIcon(icon);
                            }
                        }
                    }
                }
            } else if (type == false) { // complogic
                if (e.getSource() == reset) {
                    for (i = 0; i <= 8; i++) {
                        if (b[i] != null) {
                            b[i].setIcon(null);
                        }
                    }
                    for (i = 0; i <= 7; i++)
                        for (j = 0; j <= 4; j++)
                            a[i][j] = a1[i][j]; // again initializing array
                } else { // complogic
                    for (i = 0; i <= 8; i++) {
                        if (b[i] != null && e.getSource() == b[i]) {
                            if (b[i].getIcon() == null) {
                                b[i].setIcon(ic1);
                                if (b[4].getIcon() == null) {
                                    b[4].setIcon(ic2);
                                    this.check(5);
                                } else {
                                    this.complogic(i);
                                }
                            }
                        }
                    }
                }
            }

            for (i = 0; i <= 7; i++) {
                if (b[(a[i][1] - 1)] != null && b[(a[i][2] - 1)] != null && b[(a[i][3] - 1)] != null) {
                    Icon icon1 = b[(a[i][1] - 1)].getIcon();
                    Icon icon2 = b[(a[i][2] - 1)].getIcon();
                    Icon icon3 = b[(a[i][3] - 1)].getIcon();
                    if ((icon1 == icon2) && (icon2 == icon3) && (icon1 != null)) {
                        if (icon1 == ic1) {
                            b[(a[i][1] - 1)].setIcon(ic11);
                            b[(a[i][2] - 1)].setIcon(ic11);
                            b[(a[i][3] - 1)].setIcon(ic11);

                            // Tambahkan skor dan update label skor
                            scorePlayer1++;
                            l1.setText("Player1: " + scorePlayer1);

                            // Tambahkan tombol "Main Lagi" di samping tombol "Oke"
                            Object[] options = {"Oke", "Main Lagi"};
                            int result = JOptionPane.showOptionDialog(TTT1.this,
                                    "!!!YOU won!!! click reset",
                                    "Game Over",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);

                            if (result == 1) {
                                // Klik "Main Lagi", reset permainan
                                reset.doClick();
                            }

                            break;
                        } else if (icon1 == ic2) {
                            b[(a[i][1] - 1)].setIcon(ic22);
                            b[(a[i][2] - 1)].setIcon(ic22);
                            b[(a[i][3] - 1)].setIcon(ic22);

                            // Tambahkan skor dan update label skor
                            if (type == true) {
                                scorePlayer2++;
                                l2.setText("Player2: " + scorePlayer2);
                            } else {
                                scorePlayer2++;
                                l2.setText("Computer: " + scorePlayer2);
                            }

                            // Tambahkan tombol "Main Lagi" di samping tombol "Oke"
                            Object[] options = {"Oke", "Main Lagi"};
                            int result = JOptionPane.showOptionDialog(TTT1.this,
                                    "!!!AWK (COMPUTER) won!!! click reset",
                                    "Game Over",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);

                            if (result == 1) {
                                // Klik "Main Lagi", reset permainan
                                reset.doClick();
                            }

                            break;
                        }
                    }
                }
            }
        }
    }

// ...


    public static void main(String[] args) {
        new TTT1();
    }
}
