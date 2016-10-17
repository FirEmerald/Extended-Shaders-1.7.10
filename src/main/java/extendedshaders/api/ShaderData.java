package extendedshaders.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import extendedshaders.core.Plugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

/** Use this class to create a shader.<br>
 *  it can be a vertex shader or a fragment shader.<br>
 *  it can have a priority - higher-priority shaders are called FIRST. **/
public class ShaderData implements Comparable
{
	/** the code for this shader **/
	public String shaderText;
	/** the uniforms/variables/constants for this shader **/
	public String uniformText;
	/** the location of the code for this shader **/
	public final ResourceLocation shaderFile;
	/** the location of the uniforms/variables/constants for this shader **/
	public final ResourceLocation uniformFile;
	/** the priority of this shader **/
	public final int priority;
	/** all the custom uniform locations in this shader **/
	public final HashMap<String, Integer> uniforms = new HashMap<String, Integer>();
	/**
	 * Creates a shader.
	 * @param shaderFile the location of the shader code.
	 */
	public ShaderData(ResourceLocation shaderFile)
	{
		this(null, shaderFile, 0);
	}

	/**
	 * Creates a shader.
	 * @param uniformFile the location of the uniforms/variables/constants.
	 * @param shaderFile the location of the shader code.
	 * @param uniforms the uniforms of the shader.
	 */
	public ShaderData(ResourceLocation uniformFile, ResourceLocation shaderFile, String... uniforms)
	{
		this(uniformFile, shaderFile, 0, uniforms);
	}

	/**
	 * Creates a shader.
	 * @param shaderFile the location of the shader code.
	 * @param priority the priority of the shader.
	 */
	public ShaderData(ResourceLocation shaderFile, int priority)
	{
		this(null, shaderFile, priority);
	}

	/**
	 * Creates a shader.
	 * @param uniformFile the location of the uniforms/variables/constants.
	 * @param shaderFile the location of the shader code.
	 * @param priority the priority of the shader.
	 * @param uniforms the uniforms of the shader.
	 */
	public ShaderData(ResourceLocation uniformFile, ResourceLocation shaderFile, int priority, String... uniforms)
	{
		this.shaderFile = shaderFile;
		this.uniformFile = uniformFile;
		this.onReload(Minecraft.getMinecraft().getResourceManager());
		this.priority = priority;
		for (String uniform : uniforms) this.uniforms.put(uniform, -1);
		ReloadListener.addData(this);
	}
	
	/** gets the custom uniform locations from this program **/
	public void getUniforms(int program)
	{
		for (Map.Entry<String, Integer> entry : uniforms.entrySet()) entry.setValue(GLSLHelper.getUniformLocation(program, entry.getKey()));
	}
	
	/** loads the shader text **/
	public void onReload(IResourceManager manager)
	{
		shaderText = "";
		uniformText = "";
		if (shaderFile != null) try
		{
			shaderText = GLSLHelper.readFileAsString(shaderFile, manager) + "\n";
		}
		catch (Exception e)
		{
			Plugin.logger.error("Failed to load shader data " + shaderFile.toString(), e);
		}
		if (uniformFile != null) try
		{
			uniformText = GLSLHelper.readFileAsString(uniformFile, manager) + "\n";
		}
		catch (Exception e)
		{
			Plugin.logger.error("Failed to load uniform/function data " + uniformFile.toString(), e);
		}
	}
	
	/** used for sorting purposes **/
	@Override
	public int compareTo(Object o)
	{
		if (o instanceof ShaderData) return ((ShaderData) o).priority - priority;
		else return 0;
	}
}