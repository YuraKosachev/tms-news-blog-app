ALTER TABLE IF EXISTS public.notifications
    ALTER COLUMN sent_at DROP NOT NULL;