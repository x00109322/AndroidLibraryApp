IF OBJECT_ID(N'[__EFMigrationsHistory]') IS NULL
BEGIN
    CREATE TABLE [__EFMigrationsHistory] (
        [MigrationId] nvarchar(150) NOT NULL,
        [ProductVersion] nvarchar(32) NOT NULL,
        CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY ([MigrationId])
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20190326151633_initial')
BEGIN
    CREATE TABLE [Book] (
        [ISBN] nvarchar(450) NOT NULL,
        [Title] nvarchar(max) NOT NULL,
        [Author] nvarchar(max) NOT NULL,
        [Genre] nvarchar(max) NOT NULL,
        [Description] nvarchar(max) NULL,
        [Rating] float NOT NULL,
        [Stock] int NOT NULL,
        CONSTRAINT [PK_Book] PRIMARY KEY ([ISBN])
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20190326151633_initial')
BEGIN
    INSERT INTO [__EFMigrationsHistory] ([MigrationId], [ProductVersion])
    VALUES (N'20190326151633_initial', N'2.1.4-rtm-31024');
END;

GO

