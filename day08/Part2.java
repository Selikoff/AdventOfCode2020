package day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Solution for Part 2 of Advent of Code 2020, Day 8.
 *
 * @author Scott Selikoff
 */
public class Part2 {
	private static int runProgram(String[] instructions) {
		final boolean[] usedInstruction = new boolean[instructions.length];
		int argument = 0;
		int position = 0;
		while(position<instructions.length) {
			// Mark used
			if(!usedInstruction[position]) {
				usedInstruction[position] = true;
			} else {
				// Loop detected
				return 0;
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
		return argument;
	}

	public static void main(String[] args) throws Exception {
		final Path path = Path.of("src/day08/file2.txt");
		final List<String> data = Files.readAllLines(path);
		for(int i=0; i<data.size(); i++) {
			// Mutate data set by one argument
			final String[] instructions = data.toArray(new String[data.size()]);
			if(instructions[i].startsWith("jmp")) {
				instructions[i] = instructions[i].replace("jmp", "nop");
			} else if(instructions[i].startsWith("nop")) {
				instructions[i] = instructions[i].replace("nop", "jmp");
			} else {
				// On acc instruction, skip since nothing to mutate
				continue;
			}

			// Print result if any of them finish
			int result;
			if((result = runProgram(instructions)) != 0) {
				System.out.println(result);
			}
		}
	}
}
