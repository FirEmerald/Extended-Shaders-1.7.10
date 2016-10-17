package extendedshaders.api;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

public class PostProcessorEvent extends Event
{
	public final float partialTicks;
	public final PostProcessor processor;
	private PostProcessorEvent(float partialTicks, PostProcessor processor)
	{
		this.partialTicks = partialTicks;
		this.processor = processor;
	}
	/**
	 * This event is fired before a post-processor is run.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * {@link #processor} contains the PostProcessor to run.<br>
	 * <br>
	 * This event is {@link Cancelable}.<br>
	 * If the event is canceled, the post-processor will not run.<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	@Cancelable
	public static class Start extends PostProcessorEvent
	{
		public Start(float partialTicks, PostProcessor processor)
		{
			super(partialTicks, processor);
		}
	}

	/**
	 * This event is fired after a post-processor is run.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * {@link #processor} contains the PostProcessor to run.<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	public static class Stop extends PostProcessorEvent
	{
		public Stop(float partialTicks, PostProcessor processor)
		{
			super(partialTicks, processor);
		}
	}
}