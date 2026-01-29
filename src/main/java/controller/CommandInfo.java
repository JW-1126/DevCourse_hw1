package controller;

public record CommandInfo(CommandRegistry commandRegistry, Integer index) {
    public static CommandInfo of(String command) {
        String[] split = command.split("\\?");
        return new CommandInfo(CommandRegistry.getCommandRegistry(split[0]).get(),
                indexCheck(split));
    }

    private static Integer getIndex(String command) {
        String[] split = command.split("=");
        return Integer.parseInt(split[1]);
    }

    private static Integer indexCheck(String[] split) {
        if (split.length == 2) {
            return getIndex(split[1]);
        }
        return null;
    }
}
