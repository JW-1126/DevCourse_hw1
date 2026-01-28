package controller;

public record CommandInfo(CommandRegistry commandRegistry, Integer index) {
    public static CommandInfo of(String command) {
        String[] split = command.split("\\?");
        return new CommandInfo(CommandRegistry.getCommandRegistry(split[0]).get(),
                getIndex(split[1]));
    }

    private static Integer getIndex(String command) {
        String[] split = command.split("=");
        if (split.length != 2) {
            return null;
        }
        return Integer.parseInt(split[1]);
    }
}
