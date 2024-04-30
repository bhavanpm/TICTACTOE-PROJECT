import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;// Width of the game board in pixels
    int boardHeight = 650;  // Height of the game board in pixels

    JFrame frame = new JFrame("Tic-Tac-Toe");    // Create a JFrame object to hold our game
    JLabel textLabel = new JLabel();    // Label for displaying messages to users
    JPanel textPanel = new JPanel();    // Panel that holds the label
    JPanel boardPanel = new JPanel();    // Panel that holds the game board

    JButton[][] board = new JButton[3][3];    // Game board is an array of buttons
    String playerX = "X";      // Player X's symbol
    String playerO = "O";
    String currentPlayer = playerX;    // The player whose turn it currently is x

    boolean gameOver = false;    // Is the game over?
    int turns = 0;    // Number of moves made so far

    TicTacToe() {    // Constructor initializes the game
        frame.setVisible(true);    // Make the window visible
        frame.setSize(boardWidth, boardHeight);    // Set its size
        frame.setLocationRelativeTo(null);    // Center the window on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Close operation when closed
        frame.setLayout(new BorderLayout());    // Use border layout manager

        textLabel.setBackground(Color.darkGray);    // Set background color
        textLabel.setForeground(Color.WHITE);        // Set font color
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));    // Set font type and size
        textLabel.setHorizontalAlignment(JLabel.CENTER);       // Align text horizontally
        textLabel.setText("Tic-Tac-Toe");      // Display this message by default
        textLabel.setOpaque(true);              // Make sure the label is opaque (solid)

        textPanel.setLayout(new BorderLayout());       // Use border layout within panel
        textPanel.add(textLabel);                      // Add the label to the center of the panel
        frame.add(textPanel, BorderLayout.SOUTH);      // Place the panel at the bottom of the

        boardPanel.setLayout(new GridLayout(3, 3));    // Use grid layout with rows=cols=3
        boardPanel.setBackground(Color.darkGray);    // Set background color for the panel
        frame.add(boardPanel);                 // Add the panel to the CENTER of the frame

        for (int r = 0; r < 3; r++) {             // Loop through each row
            for (int c = 0; c < 3; c++) {          // Loop through each column
                JButton tile = new JButton();          // Create a button for this cell
                board[r][c] = tile;                // Store reference 
                boardPanel.add(tile);                // Add the button to the panel
                
                
                
                tile.setBackground(Color.yellow );    // Yellow background color
                tile.setForeground(Color.black);          // Black font color 
                tile.setFont(new Font("Arial", Font.BOLD, 120));   
                tile.setFocusable(false);                // Don't allow keyboard focus
                

                tile.addActionListener(new ActionListener() {    // Handle mouse clicks
                    public void actionPerformed(ActionEvent e) {    // When clicked...
                        JButton tile = (JButton) e.getSource();    // Get the source object (the button that was clicked)
                        if (tile.getText() == "") {                // If it's empty...
                            tile.setText(currentPlayer);               // Put an "X" or "O
                            turns++;                                    // Increment number of moves
                            checkWinner();
                            if (!gameOver) {                            // If game not over yet...
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;    // Switch players
                                textLabel.setText(currentPlayer + "'s turn.");    // Update status
                            }
                        }

                    }
                });
            }
        }
    }
    
    void checkWinner() {    // Checks to see if there has been  a winner
        for (int r = 0; r < 3; r++) {           // Check rows and columns
            if (board[r][0].getText() == "") continue;    // Skip blank cells in current iteration

            if (board[r][0].getText() == board[r][1].getText() &&    // Compare first element with second etc.
                board[r][1].getText() == board[r][2].getText()) {    // Compare first col to second & third
                for (int i = 0; i < 3; i++) {               // Mark all tiles in row as same color
                    setWinner(board[r][i]);  
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) { 
            if (board[0][c].getText() == "") continue; 
            
            if (board[0][c].getText() == board[1][c].getText() &&       // First two are the same...
                board[1][c].getText() == board[2][c].getText()) {           // And so is the last one...
                for (int i = 0; i < 3; i++) {               // Mark them all with the same color
                    setWinner(board[i][c]); 
                }
                gameOver = true; 
                return; 
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&          // Top left to bottom right
            board[1][1].getText() == board[2][2].getText() &&  
            board[0][0].getText() != "") { 
            for (int i = 0; i < 3; i++) {               // Mark them all with the top-left color
                setWinner(board[i][i]); 
            }
            gameOver = true;
            return;
        }

        //antidiagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.black);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    
    void setTie(JButton tile) { 
        tile.setForeground(Color.orange); 
        tile.setBackground(Color.gray); 
        textLabel.setText("Tie!");  
    }
}