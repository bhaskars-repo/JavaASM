/*
 * Description: Simple Java class to visit nodes of a class using ASM
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.asm;

import org.objectweb.asm.*;

public class SimpleVisitorUsingASM {
    static class SimpleMethodVisitor extends MethodVisitor {
        public SimpleMethodVisitor() {
            super(Opcodes.ASM9);
        }

        public void visitVarInsn(int opcode, int var) {
            System.out.printf("visitVarInsn: opcode = %d, var = %d\n", opcode, var);
        }

        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
            System.out.printf("visitLocalVariable: Name = %s, Desc = %s, Signature = %s, Index = %d\n",
                    name, desc, signature, index);
        }

        public void visitMaxs(int maxStack, int maxLocals) {
            System.out.printf("visitMaxs: max stack = %d, max locals = %d\n", maxStack, maxLocals);
        }
    }

    static class SimpleClassVisitor extends ClassVisitor {
        public SimpleClassVisitor() {
            super(Opcodes.ASM9);
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            System.out.printf("visitField: Name = %s, Desc = %s, Signature = %s, Value = %s\n",
                    name, desc, signature, value);

            return super.visitField(access, name, desc, signature, value);
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            System.out.printf("visitMethod: Name = %s, Desc = %s, Signature = %s\n", name, desc, signature);

            return new SimpleMethodVisitor();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Usage: java %s <class-name>\n", SimpleVisitorUsingASM.class.getName());
            System.exit(1);
        }

        try {
            ClassReader reader = new ClassReader(args[0]);
            reader.accept(new SimpleClassVisitor(), ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
