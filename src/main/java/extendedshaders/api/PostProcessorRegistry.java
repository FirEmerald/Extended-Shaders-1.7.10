package extendedshaders.api;

import java.util.ArrayList;
import java.util.Arrays;

/** used to turn post-processing effects on and off, by adding and removing them from a registry **/
public class PostProcessorRegistry
{
	private static final ArrayList<PostProcessor> shaders = new ArrayList<PostProcessor>();
	/** turns the post-processor ON **/
	public static void addShader(PostProcessor data)
	{
		shaders.add(data);
	}
	/** turns the post-processor OFF **/
	public static void removeShader(PostProcessor data)
	{
		shaders.remove(data);
	}
	/** gets the active post-processors, sorted by priority **/
	public static PostProcessor[] getShaders()
	{
		PostProcessor[] data = new PostProcessor[shaders.size()];
		data = shaders.toArray(data);
		Arrays.sort(data);
		return data;
	}
}