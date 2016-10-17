package extendedshaders.api;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

public abstract class ShaderEvent extends Event
{
	public final float partialTicks;
	private ShaderEvent(float partialTicks)
	{
		this.partialTicks = partialTicks;
	}

	/**
	 * This event is fired before the sky is rendered.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * <br>
	 * This event is {@link Cancelable}.<br>
	 * If the event is canceled, the sky will not be rendered.<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	@Cancelable
	public static class RenderSky extends ShaderEvent
	{
		public RenderSky(float partialTicks)
		{
			super(partialTicks);
		}
	}

	/**
	 * This event is fired at the beginning of a render pass.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * {@link #renderPass} is the render pass<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	public static abstract class RenderPass extends ShaderEvent
	{
		public final int renderPass;
		public RenderPass(float partialTicks, int renderPass)
		{
			super(partialTicks);
			this.renderPass = renderPass;
		}
	}

	/**
	 * This event is fired before shaders are run<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * {@link #renderPass} is the render pass, always 1.<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	public static class Start extends RenderPass
	{
		public Start(float partialTicks)
		{
			super(partialTicks, 1);
		}
	}

	/**
	 * This event is fired when settings are reset between render passes.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * {@link #renderPass} is the render pass<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	public static class Loop extends RenderPass
	{
		public Loop(float partialTicks, int renderPass)
		{
			super(partialTicks, renderPass);
		}
	}

	/**
	 * This event is fired when shaders are stopped.<br>
	 * <br>
	 * {@link #partialTicks} is the partial-tick time<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 **/
	public static class Stop extends ShaderEvent
	{
		public Stop(float partialTicks)
		{
			super(partialTicks);
		}
	}
}