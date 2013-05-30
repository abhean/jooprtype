package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuMode extends AbstractGameMode
{
	public static final String ID = "MAINMENU"; 
		
	public MainMenuMode(GameManager gameManager)
	{
		super(gameManager);
		
	}
	
	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		starField.update(timeDelta);
	}

	@Override
	public void onActivated()
	{
		
	}
	
	@Override
	public void onDeactivated()
	{
		
	}
	
	/**
	 * 
	 * @author kique
	 *
	 */
	enum Level
	{
		EASY   ("assets/leveldefs/easy.xml"),
		MEDIUM ("assets/leveldefs/medium.xml"),
		HARD   ("assets/leveldefs/hard.xml"),
		INSANE ("assets/leveldefs/insane.xml");
		
		final String getLevelDefPath()
		{
			return levelDefPath;
		}
		
		private Level(String levelDefPath)
		{
			this.levelDefPath = levelDefPath;
		}
		
		private String levelDefPath;
	}
	
	@Override
	public void onPushed()
	{
		ActionListener menuActionListener = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Level chosenLevel = Level.valueOf(e.getActionCommand());
				if   (chosenLevel != null)
				{
					getGameManager().popGameMode();
					getGameManager().loadLevel(chosenLevel.getLevelDefPath());
					getGameManager().pushGameMode(IngameMode.ID);
				}
			}
		};
		
		graphics.GraphicsManager graphicsManager = app.App.getInstance().getGraphicsManager();
		starField = graphicsManager.CreateItem(graphics.StarsField.class);
		starField.init(graphicsManager.getScreen().getSize(), 100, 1.0f);
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		mainMenuPanel.setLayout(new BorderLayout(10, 100));
		mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(100, 270, 280, 270));
		
		// Title label
		JLabel titleLabel = new JLabel("R-Type");
		titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setPreferredSize(titleLabel.getPreferredSize());
		mainMenuPanel.add(titleLabel, BorderLayout.PAGE_START);
		
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		optionsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		optionsPanel.setLayout(new FlowLayout());
		optionsPanel.setPreferredSize(new Dimension(420, 600));
		createOptionButton(optionsPanel, "Easy",   Level.EASY.toString(),   menuActionListener);
		createOptionButton(optionsPanel, "Medium", Level.MEDIUM.toString(), menuActionListener);
		createOptionButton(optionsPanel, "Hard",   Level.HARD.toString(),   menuActionListener);
		createOptionButton(optionsPanel, "Insane", Level.INSANE.toString(), menuActionListener);
		mainMenuPanel.add(optionsPanel, BorderLayout.CENTER);
		
		JFrame mainFrame = app.App.getInstance().getMainFrame();
		mainMenuPanel.setSize(mainFrame.getSize());

		mainFrame.add(mainMenuPanel, 0);
		mainMenuPanel.requestFocusInWindow();
		
		mainFrame.validate();
		mainFrame.repaint();
	}
	
	@Override
	public void onPopped()
	{
		if (starField != null)
		{
			app.App.getInstance().getGraphicsManager().deleteItem(starField);
			starField = null;
		}
		
		if (mainMenuPanel != null)
		{
			JFrame mainFrame = app.App.getInstance().getMainFrame();
			mainFrame.remove(mainMenuPanel);
			mainFrame.validate();
			mainFrame.repaint();
			
			mainMenuPanel = null;
		}
	}
			
	/**
	 * 
	 * @param labelText
	 * @param command
	 * @param listener
	 * @return
	 */
	private JButton createOptionButton(JPanel panel, String labelText, String command, ActionListener listener)
	{
		JButton optionButton = new JButton(labelText);
		optionButton.setPreferredSize(new Dimension(400, 50));
		optionButton.setActionCommand(command);
		optionButton.addActionListener(listener);
		panel.add(optionButton);
		
		return optionButton;
	}
	
	private JPanel mainMenuPanel;
	graphics.StarsField starField;
}
