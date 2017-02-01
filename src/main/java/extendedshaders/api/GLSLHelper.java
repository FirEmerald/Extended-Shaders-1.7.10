package extendedshaders.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

/** helper functions mainly for working with GLSL,
 * 	but also some other misc functions you may find usefull.
 * @author FirEmerald
 */
public class GLSLHelper
{
	/** TRUE if using the compatibility profile **/
	public static final boolean ARBSHADERS;
    private static Logger logger = LogManager.getLogger("GLSL Helper");
	static
	{
		ContextCapabilities caps = GLContext.getCapabilities();
		ARBSHADERS = caps.OpenGL21;
	}
	/** creates a new shader program **/
	public static int createProgram()
	{
		return OpenGlHelper.func_153183_d();
	}
	/** gets the location of a uniform in the shader program **/
	public static int getUniformLocation(int program, CharSequence name)
	{
		return OpenGlHelper.func_153194_a(program, name);
	}
	/** creates a vertex shader **/
	public static int createVertShader()
	{
		return OpenGlHelper.func_153195_b(GL20.GL_VERTEX_SHADER);
	}
	/** creates a fragment shader **/
	public static int createFragShader()
	{
		return OpenGlHelper.func_153195_b(GL20.GL_FRAGMENT_SHADER);
	}
	/** load the string into the shader **/
	public static void shaderSource(int shader, CharSequence string)
	{
		if (ARBSHADERS) ARBShaderObjects.glShaderSourceARB(shader, string);
		else GL20.glShaderSource(shader, string);
	}
	/** compiles the shader **/
	public static void compileShader(int shader)
	{
		OpenGlHelper.func_153170_c(shader);
	}
	/** returns TRUE if the shader DID compile successfully **/
	public static boolean didShaderCompile(int shader)
	{
		return OpenGlHelper.func_153157_c(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
	}
	/** deletes a shader **/
	public static void deleteShader(int shader)
	{
		OpenGlHelper.func_153180_a(shader);
	}
	/** gets the log for the shader **/
    public static String getShaderLog(int shader)
    {
    	return ARBSHADERS ? ARBShaderObjects.glGetInfoLogARB(shader, ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)) : GL20.glGetShaderInfoLog(shader, GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH));
    }
	/** attaches the shader to the program **/
    public static void linkShader(int program, int shader)
    {
    	OpenGlHelper.func_153178_b(program, shader);
    }
    /** finalizes the program after linking shaders **/
    public static void linkProgram(int program)
    {
    	OpenGlHelper.func_153179_f(program);
    }
    /** returns TRUE if the shaders linked to the program successfully **/
	public static boolean didProgramLink(int program)
	{
		return OpenGlHelper.func_153175_a(program, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
	}
	/** runs a validation check on the program to ensure nothing goes wrong **/
	public static void validateProgram(int program)
	{
		if (ARBSHADERS) ARBShaderObjects.glValidateProgramARB(program);
		else GL20.glValidateProgram(program);
	}
	/** returns TRUE if the program validated. **/
	public static boolean didProgramValidate(int program)
	{
		return (ARBSHADERS ? ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) : GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS)) == GL11.GL_TRUE;
	}
	/** gets the program log. **/
    public static String getProgramLog(int program)
    {
    	return ARBSHADERS ? ARBShaderObjects.glGetInfoLogARB(program, ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)) : GL20.glGetProgramInfoLog(program, GL20.glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH));
    }
    /** sets the active shader program **/
	public static void runProgram(int program)
	{
		OpenGlHelper.func_153161_d(program);
	}
	/** deletes a program **/
	public static void deleteProgram(int program)
	{
		OpenGlHelper.func_153187_e(program);
	}
	/** sets a uniform integer type (bool, int, sampler2D, ect.) **/
	public static void uniform1i(int location, int val)
	{
		OpenGlHelper.func_153163_f(location, val);
	}
	/** sets a uniform float type **/
	public static void uniform1f(int location, float val)
	{
    	if (ARBSHADERS) ARBShaderObjects.glUniform1fARB(location, val);
    	else GL20.glUniform1f(location, val);
	}
	/** sets a uniform 2-int type **/
	public static void uniform2i(int location, int val1, int val2)
	{
    	if (ARBSHADERS) ARBShaderObjects.glUniform2iARB(location, val1, val2);
    	else GL20.glUniform2i(location, val1, val2);
	}
	/** sets a uniform 2-float (vec2) type **/
	public static void uniform2f(int location, float val1, float val2)
	{
    	if (ARBSHADERS) ARBShaderObjects.glUniform2fARB(location, val1, val2);
    	else GL20.glUniform2f(location, val1, val2);
	}
	/** sets a uniform 3-int type **/
	public static void uniform3i(int location, int val1, int val2, int val3)
	{
    	if (ARBSHADERS) ARBShaderObjects.glUniform3iARB(location, val1, val2, val3);
    	else GL20.glUniform3i(location, val1, val2, val3);
	}
	/** sets a uniform 3-float (vec3) type **/
	public static void uniform3f(int location, float val1, float val2, float val3)
	{
		if (ARBSHADERS) ARBShaderObjects.glUniform3fARB(location, val1, val2, val3);
		else GL20.glUniform3f(location, val1, val2, val3);
	}
	/** sets a uniform 4-int type **/
	public static void uniform4i(int location, int val1, int val2, int val3, int val4)
	{
    	if (ARBSHADERS) ARBShaderObjects.glUniform4iARB(location, val1, val2, val3, val4);
    	else GL20.glUniform4i(location, val1, val2, val3, val4);
	}
	/** sets a uniform 4-float (vec4) type **/
	public static void uniform4f(int location, float val1, float val2, float val3, float val4)
	{
		if (ARBSHADERS) ARBShaderObjects.glUniform4fARB(location, val1, val2, val3, val4);
		else GL20.glUniform4f(location, val1, val2, val3, val4);
	}
	/** sets a uniform integer (bool, int, sampler2D, ect.) type array **/
	public static void uniform1(int location, IntBuffer val)
	{
		OpenGlHelper.func_153181_a(location, val);
	}
	/** sets a uniform float type array **/
	public static void uniform1(int location, FloatBuffer val)
	{
		OpenGlHelper.func_153168_a(location, val);
	}
	/** sets a uniform 2-integer type array **/
	public static void uniform2(int location, IntBuffer val)
	{
		OpenGlHelper.func_153182_b(location, val);
	}
	/** sets a uniform 2-float (vec2) type array **/
	public static void uniform2(int location, FloatBuffer val)
	{
		OpenGlHelper.func_153177_b(location, val);
	}
	/** sets a uniform 3-integer type array **/
	public static void uniform3(int location, IntBuffer val)
	{
		OpenGlHelper.func_153192_c(location, val);
	}
	/** sets a uniform 3-float (vec3) type array **/
	public static void uniform3(int location, FloatBuffer val)
	{
		OpenGlHelper.func_153191_c(location, val);
	}
	/** sets a uniform 4-integer type array **/
	public static void uniform4(int location, IntBuffer val)
	{
		OpenGlHelper.func_153162_d(location, val);
	}
	/** sets a uniform 4-float (vec4) type array **/
	public static void uniform4(int location, FloatBuffer val)
	{
		OpenGlHelper.func_153159_d(location, val);
	}
	/** sets a uniform mat2 **/
    public static void uniformMat2(int location, boolean transpose, FloatBuffer val)
    {
    	OpenGlHelper.func_153173_a(location, transpose, val);
    }
	/** sets a uniform mat3 **/
    public static void uniformMat3(int location, boolean transpose, FloatBuffer val)
    {
    	OpenGlHelper.func_153189_b(location, transpose, val);
    }
	/** sets a uniform mat4 **/
    public static void uniformMat4(int location, boolean transpose, FloatBuffer val)
    {
    	OpenGlHelper.func_153160_c(location, transpose, val);
    }
    /** reads a text file as a single string of text **/
    public static String readFileAsString(ResourceLocation loc, IResourceManager manager) throws Exception
    {
    	InputStream in = manager.getResource(loc).getInputStream();
        StringBuilder source = new StringBuilder();
        Exception exception = null;
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            Exception innerExc= null;
            try
            {
                String line;
                while((line = reader.readLine()) != null) source.append(line).append('\n');
            }
            catch(Exception exc)
            {
                exception = exc;
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch(Exception exc)
                {
                    if(innerExc == null) innerExc = exc;
                    else exc.printStackTrace();
                }
            }
            if(innerExc != null) throw innerExc;
        }
        catch(Exception exc)
        {
            exception = exc;
        }
        finally
        {
            try
            {
                in.close();
            }
            catch(Exception exc)
            {
                if (exception == null) exception = exc;
                else exc.printStackTrace();
            }
            if (exception != null) throw exception;
        }
        return source.toString();
    }
    /** checks for OpenGL errors and prints them to the log **/
    public static void checkGLErrors(String location)
    {
        int i = GL11.glGetError();
        if (i != 0)
        {
            String s1 = GLU.gluErrorString(i);
            logger.error("########## GL ERROR ##########");
            logger.error("@ " + location);
            logger.error(i + ": " + s1);
            while ((i = GL11.glGetError()) != 0) logger.error(i + ": " + s1);
        }
    }
}