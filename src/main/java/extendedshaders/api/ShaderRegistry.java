package extendedshaders.api;

import java.util.ArrayList;
import java.util.Arrays;

/** used to turn shaders on and off, by adding and removing them from a registry.<br>
 * keep in mind changes do not effect until the beginning of the next render tick, or when {@link Passthrough#forceShaderCompile()} is run. **/
public class ShaderRegistry
{
	private static final ArrayList<ShaderData> vertexShaders = new ArrayList<ShaderData>();
	private static final ArrayList<ShaderData> fragmentShaders = new ArrayList<ShaderData>();
	public static boolean hasChanged = true;
	/** returns TRUE while shaders are running **/
	public static boolean shadersActive = false;
	/** turns a vetex shader ON **/
	public static void addVertexShader(ShaderData data)
	{
		vertexShaders.add(data);
		hasChanged = true;
	}
	/** turns a vetex shader OFF **/
	public static void removeVertexShader(ShaderData data)
	{
		vertexShaders.remove(data);
		hasChanged = true;
	}
	/** gets the active vertex shaders, sorted by priority **/
	public static ShaderData[] getVertexShaders()
	{
		ShaderData[] data = new ShaderData[vertexShaders.size()];
		data = vertexShaders.toArray(data);
		Arrays.sort(data);
		return data;
	}
	/** turns a fragment shader ON **/
	public static void addFragmentShader(ShaderData data)
	{
		fragmentShaders.add(data);
	}
	/** turns a fragment shader OFF **/
	public static void removeFragmentShader(ShaderData data)
	{
		fragmentShaders.remove(data);
	}
	/** gets the active fragment shaders, sorted by priority **/
	public static ShaderData[] getFragmentShaders()
	{
		ShaderData[] data = new ShaderData[fragmentShaders.size()];
		data = fragmentShaders.toArray(data);
		Arrays.sort(data);
		return data;
	}
}