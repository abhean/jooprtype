package view.hud;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerView
{
	public PlayerView(WorldView worldView, model.Player player)
	{
		this.worldView = worldView;
		this.player = player;
		
		playerPanel = new JPanel();
		playerPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		playerPanel.setSize(200, 200);
		
		nameLabel = new JLabel(player.getName());
		nameLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		nameLabel.setHorizontalAlignment(JLabel.LEFT);
		nameLabel.setForeground(player.getColor());
		nameLabel.setPreferredSize(nameLabel.getPreferredSize());
		playerPanel.add(nameLabel);
		
		scoreLabel = new JLabel("0");
		scoreLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		scoreLabel.setForeground(Color.YELLOW);
		scoreLabel.setPreferredSize(nameLabel.getPreferredSize());
		playerPanel.add(scoreLabel);

		playerPanel.revalidate();
		
		worldView.addPlayerPanel(playerPanel);
	}
	
	public void dispose()
	{
		if (playerPanel != null)
		{
			worldView.removePlayerPanel(playerPanel);
			playerPanel = null;
		}
		
		scoreLabel = null;
		nameLabel = null;
	}

	public void update(final float timeDelta)
	{
		scoreLabel.setText(Integer.toString(player.getScore()));
	}
	
	
	private WorldView worldView;
	private model.Player player;
	
	private JLabel nameLabel;
	private JLabel scoreLabel;
	private JPanel playerPanel;
}
