package extendedshaders.api;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

/** DO NOT TOUCH THESE METHODS. they are used to ensure shaders and post-processors reload when resources are reloaded, even if Extended Shaders is not installed **/
public class ReloadListener implements IResourceManagerReloadListener
{
	private static ArrayList<WeakReference<ShaderData>> data = new ArrayList<WeakReference<ShaderData>>();
	private static ArrayList<WeakReference<PostProcessor>> post = new ArrayList<WeakReference<PostProcessor>>();
	static
	{
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new ReloadListener());
	}
	
	protected static void addData(ShaderData shaderData)
	{
		data.add(new WeakReference<ShaderData>(shaderData));
	}
	
	protected static void addPost(PostProcessor postProcessor)
	{
		post.add(new WeakReference<PostProcessor>(postProcessor));
	}

	@Override
	public void onResourceManagerReload(IResourceManager manager)
	{
		ShaderRegistry.hasChanged = true;
		int size = data.size();
		for (int i = 0; i < size; i++)
		{
			WeakReference<ShaderData> reference = data.get(i);
			if (reference.get() == null)
			{
				data.remove(i);
				i--;
				size--;
			}
			else reference.get().onReload(manager);
		}
		size = post.size();
		for (int i = 0; i < size; i++)
		{
			WeakReference<PostProcessor> reference = post.get(i);
			if (reference.get() == null)
			{
				post.remove(i);
				i--;
				size--;
			}
			else reference.get().onReload(manager);
		}
	}
}