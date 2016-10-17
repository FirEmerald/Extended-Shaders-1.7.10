package extendedshaders.api;

import java.util.ArrayList;

/** used to request multiple render passes to take place. **/
public class RenderPassRegistry
{
	public static int renderPasses = 1;
	private static boolean hasChanged = true;
	private static final ArrayList<RenderPassRegistry> tickets = new ArrayList<RenderPassRegistry>();
	/** requests render passes **/
	public static void registerRenderpassesTicket(RenderPassRegistry ticket)
	{
		tickets.add(ticket);
		hasChanged = true;
	}
	/** unrequests render passes **/
	public static void unregisterRenderpassesTicket(RenderPassRegistry ticket)
	{
		tickets.remove(ticket);
		hasChanged = true;
	}
	/** called by Extended Shaders to determine the number of render passes to run each render tick **/
	public static void update()
	{
		if (hasChanged)
		{
			renderPasses = 1;
			for (RenderPassRegistry registry : tickets) if (registry.numPasses > renderPasses) renderPasses = registry.numPasses;
			hasChanged = false;
		}
	}
	private final int numPasses;
	/** constructs a ticket with the specified number of render passes **/
	public RenderPassRegistry(int numPasses)
	{
		this.numPasses = numPasses;
	}
}