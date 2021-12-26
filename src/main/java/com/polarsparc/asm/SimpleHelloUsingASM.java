/*
 * Description: Simple Java class to print '*** Hello using ASM !!!' created using ASM
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;

public class SimpleHelloUsingASM {
    private static void createJavaClass(ClassWriter writer) {
        writer.visit(Opcodes.V17,
                Opcodes.ACC_PUBLIC,
                "com/polarsparc/asm/SimpleHelloASM",
                null,
                "java/lang/Object",
                null);
    }

    private static void createDefaultConstructor(ClassWriter writer) {
        MethodVisitor visitor = writer.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        visitor.visitCode();
        visitor.visitVarInsn(Opcodes.ALOAD, 0); // this
        visitor.visitMethodInsn(Opcodes.INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V",
                false);
        visitor.visitInsn(Opcodes.RETURN);
        visitor.visitMaxs(1, 1);
        visitor.visitEnd();
    }

    private static void createStaticMainMethod(ClassWriter writer) {
        MethodVisitor visitor = writer.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);
        visitor.visitCode();
        visitor.visitFieldInsn(Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");
        visitor.visitLdcInsn("*** Hello using ASM !!!");
        visitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false);
        visitor.visitInsn(Opcodes.RETURN);
        visitor.visitMaxs(2, 1);
        visitor.visitEnd();
        writer.visitEnd();
    }

    static class ByteCodeClassLoader extends ClassLoader {
        public Class<?> defile(String name, byte[] code) {
            return super.defineClass(name, code, 0, code.length);
        }
    }

    public static void main(String[] args) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        createJavaClass(writer);
        createDefaultConstructor(writer);
        createStaticMainMethod(writer);

        ByteCodeClassLoader loader = new ByteCodeClassLoader();

        try {
            Class<?> helloUsingASMClazz = loader.defile("com.polarsparc.asm.SimpleHelloASM",
                    writer.toByteArray());
            Method main = helloUsingASMClazz.getMethod("main", String[].class);
            main.invoke(null, (Object) new String[] {});
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
