package game;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverMode extends AbstractGameMode
{
	public static final String ID = "GAMEOVER"; 
	
	public GameOverMode(GameManager gameManager)
	{
		super(gameManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(final float timeDelta)
	{
		mandatoryVisibleTimeLeft -= timeDelta;
	}
	
	
	@Override
	public void onPushed()
	{
		mandatoryVisibleTimeLeft = 0.5f;
		
		gameOverPanel = new JPanel();
		gameOverPanel.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (mandatoryVisibleTimeLeft < 0.0f)
				{
					getGameManager().popAllGameModes();
					getGameManager().pushGameMode(MainMenuMode.ID);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{

			}
		});
		
		gameOverPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		gameOverPanel.setLayout(new BorderLayout());
		gameOverPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
		
		// Game over label
		GameManager gameManager = getGameManager();
		model.World world = gameManager.getWorld();

		// Select highest score ALIVE player
		List<model.Player> players = world.getPlayers();
		model.Player winnerPlayer = null;
		for (model.Player player: players)
		{
			model.Entity playerEntity = player.getEntity();
			model.LifeComponent playerLifeComp = playerEntity.getComponentByType(model.LifeComponent.class);
			
			if (playerLifeComp.getState() == model.LifeComponent.State.ALIVE)
			{
				if ((winnerPlayer == null) || (player.getScore() > winnerPlayer.getScore()))
				{
					winnerPlayer = player;
				}
			}
		}
		
		String gameOverText = "<html><p align=\"center\">GAME OVER!<br>";
		if (winnerPlayer != null)
		{
			gameOverText += winnerPlayer.getName() + " WINS!<br>SCORE: "+Integer.toString(winnerPlayer.getScore())+"<br>";
		}
		else
		{
			gameOverText += "ALIENS WIN!<br>";
		}
		gameOverText += "</p></html>";
		
		JLabel gameOverLabel = new JLabel(gameOverText);
		gameOverLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 45));
		gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
		gameOverLabel.setForeground(Color.WHITE);
		gameOverLabel.setPreferredSize(gameOverLabel.getPreferredSize());
		gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
		gameOverPanel.validate();

		// Press to continue
		JLabel pressToMainMenuLabel = new JLabel("Press any key to go back to Main Menu");
		pressToMainMenuLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
		pressToMainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
		pressToMainMenuLabel.setForeground(Color.WHITE);
		pressToMainMenuLabel.setPreferredSize(pressToMainMenuLabel.getPreferredSize());
		gameOverPanel.add(pressToMainMenuLabel, BorderLayout.PAGE_END);
		gameOverPanel.validate();
		
		JFrame mainFrame = app.App.getInstance().getMainFrame();
		gameOverPanel.setSize(mainFrame.getSize());

		mainFrame.add(gameOverPanel, 0);
		gameOverPanel.requestFocusInWindow();

		mainFrame.validate();
		mainFrame.repaint();
	}
	
	@Override
	public void onPopped()
	{
		if (gameOverPanel != null)
		{
			JFrame mainFrame = app.App.getInstance().getMainFrame();
			mainFrame.remove(gameOverPanel);
			mainFrame.validate();
			mainFrame.repaint();
			
			gameOverPanel = null;
		}
	}
	
	
	private JPanel gameOverPanel;
	private float mandatoryVisibleTimeLeft;
	
}
