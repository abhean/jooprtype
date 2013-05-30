package view.hud;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Entity;
import model.Player;
import model.World;

public class WorldView implements view.WorldView
{

	/**
	 * 
	 * @param world
	 */
	public WorldView()
	{
		playerViews = new LinkedList<PlayerView>();

		JFrame mainFrame = app.App.getInstance().getMainFrame();
		hudPanel = new JPanel();
		hudPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		hudPanel.setFocusable(false);
		hudPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		hudPanel.setSize(mainFrame.getSize());
		mainFrame.add(hudPanel, 0);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	/**
	 * 
	 * @param world
	 */
	@Override
	public void init(World world)
	{
		this.world = world;
		
		world.forEachPlayer(new model.World.PlayerAction() 
		{
			@Override
			public void applyTo(Player player, Object param)
			{
				onPlayerCreated(player);
			}
		}, null);
	}
	
	@Override
	public void dispose()
	{
		for (PlayerView playerView: playerViews)
		{
			playerView.dispose();
		}
		playerViews.clear();
		
		
		if (hudPanel != null)
		{
			JFrame mainFrame = app.App.getInstance().getMainFrame();
			mainFrame.remove(mainFrame);
			mainFrame.revalidate();
		}
	}

	@Override
	public void update(final float timeDelta)
	{
		// TODO Auto-generated method stub
		for (PlayerView playerView: playerViews)
		{
			playerView.update(timeDelta);
		}
		
	}
	
	@Override
	public void onEntityCreated(Entity entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityDeleting(Entity entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerCreated(Player player)
	{
		// TODO Auto-generated method stub
		PlayerView playerView = new PlayerView(this, player);
		playerViews.add(playerView);
	}
	
	/**
	 * 
	 * @param playerPanel
	 */
	public void addPlayerPanel(JPanel playerPanel)
	{
		hudPanel.add(playerPanel);
		hudPanel.revalidate();
	}
	
	/**
	 * 
	 * @param playerPanel
	 */
	public void removePlayerPanel(JPanel playerPanel)
	{
		hudPanel.remove(playerPanel);
	}

	private World world;
	private List<PlayerView> playerViews;
	private JPanel hudPanel;

}
