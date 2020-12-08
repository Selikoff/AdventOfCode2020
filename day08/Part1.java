package day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Solution for Part 1 of Advent of Code 2020, Day 8.
 * 
 * @author Scott Selikoff
 */
public class Part1 {
	private static void runProgram(String[] instructions) {
		final boolean[] usedInstruction = new boolean[instructions.length];
		int argument = 0;
		int position = 0;
		while(true) {
			// Mark used
			if(!usedInstruction[position]) {
				usedInstruction[position] = true;
			} else {
				System.out.println("Loop detected! "+argument);
				return;
			}
			
			// Process instruction
			final String instruction = instructions[position];
			final int value = Integer.parseInt(instruction.substring(instruction.indexOf(" ")+1));
			if(instruction.startsWith("nop")) {
				position++;
			} else if(instruction.startsWith("acc")) {
				argument += value;
				position++;
			} else if(instruction.startsWith("jmp")) {
				position += value;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		final Path p = Path.of("src/day08/file1.txt");
		final List<String> data = Files.readAllLines(p);
		runProgram(data.toArray(new String[data.size()]));
	}
}
